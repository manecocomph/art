package com.maneco.peanut.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.maneco.peanut.Cache;
import com.maneco.peanut.Constant;
import com.maneco.peanut.category.Product;
import com.maneco.peanut.category.Species;

public class CategoryDao {
	private CategoryDao() {
		// 
	}
	
	//TODO need shunxu
	@SuppressWarnings("unchecked")
	public static List<Species> loadSpecies(int productId) throws SQLException, UnsupportedEncodingException {
		Object obj = Cache.get(Constant.CACHE_DOMAIN_SPECIES, Integer.valueOf(productId));
		
		if (null != obj) {
			return (List<Species>) obj;
		}
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Species> speciesList = null;

		try {
			conn = DataSourceMgr.getConnection();
			ps = conn.prepareStatement("select * from t_d_species x where x.product_id_ = ? order by x.priority_ desc");
			ps.setInt(1, productId);
			rs = ps.executeQuery();
			speciesList = new ArrayList<Species>();
			int id = 0;
			String nameCn = null;
			String nameEn = null;
			String desc = null;
			int priority = 0;
			String keyWords = null;
			Date insertTime = null;

			while (rs.next()) {
				id = rs.getInt("id_");
				nameCn = new String(rs.getString("name_cn_").getBytes("ISO-8859-1"), "GBK");
				nameEn = rs.getString("name_en_");
				desc = rs.getString("desc_");
				priority = rs.getInt("priority_");
				keyWords = new String(rs.getString("key_words_").getBytes("ISO-8859-1"), "GBK");
				desc = rs.getString("desc_");
				insertTime = rs.getDate("insert_time_");
				
				speciesList.add(new Species(id, productId, nameCn, nameEn, priority, keyWords, desc, insertTime));
			}
		} finally {
			if (null != rs) {
				rs.close();
			}

			if (null != ps) {
				ps.close();
			}

			/*
			 * if (null != conn) { conn.close(); }
			 */
		}
		Cache.putToCache(Constant.CACHE_DOMAIN_SPECIES, Integer.valueOf(productId), speciesList);
		return speciesList;
	}
	
	public static Product loadProduct(int productId) throws SQLException, UnsupportedEncodingException {
		Object obj = Cache.get(Constant.CACHE_DOMAIN_PRODUCT, Integer.valueOf(productId));
		
		if (null != obj) {
			return (Product) obj;
		}
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Product p = null;
		try {
			conn = DataSourceMgr.getConnection();
			ps = conn.prepareStatement("select * from t_d_product x where x.id_ = ?");
			ps.setInt(1, productId);
			rs = ps.executeQuery();
			String nameCn = null;
			String nameEn = null;
			String code = null;
			Date insertTime = null;

			if (rs.next()) {
				nameCn = new String(rs.getString("name_cn_").getBytes("ISO-8859-1"), "GBK");
				nameEn = rs.getString("name_en_");
				code = rs.getString("code_");
				insertTime = rs.getDate("insert_time_");
				p = new Product(productId, nameCn, nameEn, code, insertTime);
			}
		} finally {
			if (null != rs) {
				rs.close();
			}

			if (null != ps) {
				ps.close();
			}

			/*
			 * if (null != conn) { conn.close(); }
			 */
		}
		
		Cache.putToCache(Constant.CACHE_DOMAIN_PRODUCT, Integer.valueOf(productId), p);
		return p;
	}
}
