package com.maneco.peanut;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.maneco.peanut.category.Product;
import com.maneco.peanut.category.Species;
import com.maneco.peanut.dao.CategoryDao;
import com.maneco.peanut.dao.RawPriceDao;
import com.maneco.peanut.price.RawPrice;
import com.maneco.peanut.region.Area;
import com.maneco.peanut.region.City;
import com.maneco.peanut.region.Country;
import com.maneco.peanut.region.Province;

public class FileDataExtractor implements Runnable {
	private File folder;
	private String fileMarker;
	private static final Country country = Country.newCountry("China");
	private static boolean ignoreParseErr = true;
	
	private FileDataExtractor() {
		// must use getInstance method
	}
	
	public static FileDataExtractor getInstance(File folder, String fileMarker, int productId) throws Exception {
		if (null == folder || !folder.exists() || folder.isFile()) {
			throw new Exception("The folder not exist: " + folder);
		}
		
		if (null == fileMarker || 0 == fileMarker.length()) {
			throw new Exception("The file marker is invalid " + fileMarker);
		}
		
		FileDataExtractor fde = new FileDataExtractor();
		fde.folder = folder;
		fde.fileMarker = fileMarker;
		
		Thread.currentThread().setName("p" + productId);
		
		return fde;
	}

	private void extractorDir() throws Exception {
		for (File file : this.folder.listFiles()) {
			if (file.getName().contains(fileMarker)) {
				FileDataExtractor.extractor(file);
			}
		}
	}
	
	private static void extractor(File file) throws Exception {
		if (null == file || !file.exists() || !file.isFile()) {
			return;
		}
		int index = file.getName().indexOf(".txt");
		if (index < 10) {
			return;
		}
		
		//System.out.println(file.getName());
		int fileLogId = FileDataExtractor.saveFileInfo(file.getName());
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine();
		
		while (null != line) {
			FileDataExtractor.extractLine(line, fileLogId);
			line = br.readLine();
		}
	}
	
	private static int saveFileInfo(String fileName) throws Exception {
		int index = fileName.indexOf(".txt");
		String dateStr = fileName.substring(index - 10, index);
		//System.out.println(dateStr);
		//TODO thread problem?
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
		return RawPriceDao.saveFileInfo(fileName, SDF.parse(dateStr));
	}
	
	private static void extractLine(String line, int dateLogId) throws Exception {
		int index = -1;

		Map<Integer, Province> indexMap = new TreeMap<Integer, Province>();
		
		for (Province province : FileDataExtractor.country.getProvinces()) {
			index = line.indexOf(province.getName());
			while (index >= 0) {
				indexMap.put(index, province);
				index = line.indexOf(province.getName(), index + 2);
			}
		}
		
		if (0 == indexMap.size()) {
			return;
		}
		
		boolean isFirst = true;
		int previousIndex = -1;
		Province previousProvince = null;
		String str = null;
		for (Map.Entry<Integer, Province> entry: indexMap.entrySet()) {
			if (isFirst) {
				isFirst = false;
			} else {
				str = line.substring(previousIndex, entry.getKey());
				RawPrice rp = FileDataExtractor.parseOneProvince(str, previousProvince);
				rp.setDateId(dateLogId);
				RawPriceDao.saveRawPrice(rp);
			}
			previousIndex = entry.getKey();
			previousProvince = entry.getValue();
		}
		
		// process the last one with the line's length
		str = line.substring(previousIndex, line.length());
		RawPrice rp = FileDataExtractor.parseOneProvince(str, previousProvince);
		rp.setDateId(dateLogId);
		RawPriceDao.saveRawPrice(rp);
	}
	
	private static RawPrice parseOneProvince(String str, Province province) throws Exception {
		int unitIndex = -1;
		int unitBeginIndex = 0;
		String unitStr = null;
		float price = 0.0f;
		int species = 0;
		RawPrice rp = new RawPrice();
		rp.setProvinceId(province.getId());
		
		// 先不考虑一个省的块里面包含多个县或市的情况
		// 暂时认为一个省块里面只包含一个县或者市
		unitIndex = str.indexOf(Constant.UNIT_CATTY);
		while (3 < unitIndex) {
			unitStr = str.substring(unitBeginIndex, unitIndex);
			//System.out.println(unitStr);
			//Main.bw.write(unitStr);
			if (unitStr.matches(".*\\d.*")) {
				price = FileDataExtractor.parsePrice(unitStr);
				species = FileDataExtractor.parseSpecies(unitStr);
				//System.out.println("species: " + species);
				rp.setPrice(price, species);
			}
			
			unitBeginIndex = unitIndex + 3;
			unitIndex = str.indexOf(Constant.UNIT_CATTY, unitIndex + 1);
		}
		
		unitBeginIndex = 0;
		unitIndex = str.indexOf(Constant.UNIT_TON);
		while (3 < unitIndex) {
			//System.out.println(unitBeginIndex + "  " + unitIndex);
			unitStr = str.substring(unitBeginIndex, unitIndex);
			//System.out.println(unitStr);
			//Main.bw.write(unitStr);
			price = FileDataExtractor.parsePrice(unitStr);
			species = FileDataExtractor.parseSpecies(unitStr);
			rp.setPrice(price / 1000, species);
			
			unitBeginIndex = unitIndex + 3;
			unitIndex = str.indexOf(Constant.UNIT_TON, unitIndex + 1);
		}
		
		int cityId = FileDataExtractor.parseCity(str, province);
		int areaId = FileDataExtractor.parseArea(str, province);
		
		rp.setCityId(cityId);
		rp.setAreaId(areaId);
		
		return rp;
	}
	
	private static int parseSpecies(String str) throws Exception {
		String name = Thread.currentThread().getName();
		String[] paras = name.split("`");
		int productId = 1; // default as peanut
		for (String para : paras) {
			if (para.startsWith("p")) {
				productId = Integer.parseInt(para.substring(1, para.length()));
			}
		}
		//System.out.println(paras);
		//System.out.println(productId);
		Product p = CategoryDao.loadProduct(productId);
		if (null == p.getSpecies()) {
			return Constant.COMMON_SPECIES;
		} else {
			//TODO need shunxu
			List<Species> speciesList = p.getSpecies();
			//System.out.println("speciesList: " + speciesList.size());
			for (Species sp : speciesList) {
				String keywords = sp.getKeyWords();
				//System.out.println("keywords: " + keywords);
				if (null != keywords && 0 < keywords.trim().length()) {
					String[] keys = keywords.split(",");
					for (String key : keys) {
						//System.out.println("key: " + key);
						if (str.matches(".*" + key + ".*")) {
							return sp.getId();
						}
					}
				} else {
					if (str.matches(".*" + sp.getNameCn() + ".*")) {
						return sp.getId();
					}
				}
				
			}
			
			return Constant.COMMON_SPECIES;
		}
		
	}
	/**
	 * 这个参数字符串是单位 元/斤 或者 元/吨 之前的字符串，不包含单位，
	 * 所以从后往前找数字，一旦数字结束，就结束。
	 * 中间允许 空格 和破折线
	 * @param str
	 * @return
	 */
	private static float parsePrice(String str) {
		char cha = ' ';
		boolean isNumberStarted = false;
		int index = str.length(), start, end = index, i = 1;
		String numberPart = "";
		float price1 = 0.0f;
		float price2 = 0.0f;
		
		try {
			// iterator every char to parse the number price
			do {
				// fetch the char and check its value
				cha = str.charAt(index - i++);
				
				if (('0' <= cha && '9' >= cha) || '.' == cha) {
					if (!isNumberStarted) {
						end = i;
						isNumberStarted = true;
					}
				} else if ('-' == cha || ' ' == cha) {
					if (isNumberStarted) {
						start = i;
						isNumberStarted = false;
						numberPart = str.substring(index - start + 2, index - end + 2);
						try {
							if (0 >= price1) {
								price1 = Float.parseFloat(numberPart);
							} else if (0 >= price1) {
								price2 = Float.parseFloat(numberPart);
							} else {
								break;
							}
						} catch(NumberFormatException nfe) {
							// ignore this exception
						}
					}
					
				} else {
					if (isNumberStarted) {
						start = i;
						numberPart = str.substring(index - start + 2, index - end + 2);
						try {
							if (0 >= price1) {
								price1 = Float.parseFloat(numberPart);
							} else if (0 >= price2) {
								price2 = Float.parseFloat(numberPart);
							} else {
								break;
							}
						} catch(NumberFormatException nfe) {
							// ignore
						}
					}
					break;
				}
			} while (true);
			
		} catch (RuntimeException re) {
			// ignore this one or not?
			if (!FileDataExtractor.ignoreParseErr) {
				throw re;
			}
		}
		if (0 < price1 && 0.0f >= price2) {
			return price1;
		} else if (0 < price1 && 0 < price2) {
			return (price1 + price2) / 2;
		} else {
			return 0.0f;
		}
	}
	
	private static int parseCity(String str, Province province) throws Exception {
		for (City city : province.getCities()) {
			if (str.contains(city.getName())) {
				return city.getId();
			}
		}
		
		return -1;
	}
	
	private static int parseArea(String str, Province province) throws Exception {
		for (City city : province.getCities()) {
			for (Area area : city.getAreas()) {
				if (str.contains(area.getName())) {
					return area.getId();
				}
			}

		}
		
		return -1;
	}
	
	@Override
	public void run() {
		try {
			this.extractorDir();
			System.out.println("Thread : " + Thread.currentThread().getName() + " " + this.fileMarker + " over!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		/*File f = new File("C:\\work\\ruby\\peanut1");
		try {
			FileDataExtractor food21 = FileDataExtractor.getInstance(f, "21Food", 1);
			FileDataExtractor foodqs = FileDataExtractor.getInstance(f, "foodqs", 1);
			FileDataExtractor chinaGrain = FileDataExtractor.getInstance(f, "chinaGrain", 1);
			FileDataExtractor jinNong = FileDataExtractor.getInstance(f, "jinNong", 1);
			FileDataExtractor ocn = FileDataExtractor.getInstance(f, "ocn", 1);
			
			new Thread(food21).start();
			new Thread(foodqs).start();
			new Thread(chinaGrain).start();
			new Thread(jinNong).start();
			new Thread(ocn).start();
			
			
			//Thread.currentThread().setName("p1");
			//System.out.println(FileDataExtractor.parseSpecies("统货小白沙花生米4.56元/斤"));
			
		} catch (Exception e) {
			e.printStackTrace();
			Main.bw.flush();
			Main.bw.close();
		}*/

		/*String str = "吉林白城林海镇：统米2.60-2.65元/斤，统果1.85-1.90元/斤，过拣石机1.90-1.95元/斤元/斤.风选过1.95-2.0.油料米2.30元/斤。（本人常年供应四粒红花生果、花生米，代收种子，保证芽率。愿扩大代收业务。本着互惠互利的原则，竭诚为您服务。电话13689778535）";
		System.out.print(str.indexOf("山东"));
		FileDataExtractor.extractLine(str, 999);*/
		Thread.currentThread().setName("p1");
		File file = new File("C:/work/ruby/test/eric2007-03-26.txt");
		try {
			FileDataExtractor.extractor(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
