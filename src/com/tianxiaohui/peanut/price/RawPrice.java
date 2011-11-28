package com.tianxiaohui.peanut.price;

public class RawPrice {
	public RawPrice() {
	}
	
	public RawPrice(int dateId) {
		this.dateId = dateId;
	}
	
	public RawPrice(int dateId, int provinceId) {
		this.dateId = dateId;
		this.provinceId = provinceId;
	}

	private int id;
	private int dateId;
	private int provinceId;
	private int cityId;
	private int areaId;
	private float price1;
	private int price1Species;
	private float price2;
	private int price2Species;
	private float price3;
	private int price3Species;
	private float price4;
	private int price4Species;
	private float price5;
	private int price5Species;
	
	// Don't consider muilti-thread
	private int count = 1;
	public void setPrice(float price, int speciesId) {
		if (5 < count) {
			return;
		}
		
		if (1 == count) {
			this.setPrice1(price);
			this.setPrice1Species(speciesId);
		} else if (2 == count) {
			this.setPrice2(price);
			this.setPrice2Species(speciesId);
		} else if (3 == count) {
			this.setPrice3(price);
			this.setPrice3Species(speciesId);
		} else if (4 == count) {
			this.setPrice4(price);
			this.setPrice4Species(speciesId);
		} else if (5 == count) {
			this.setPrice5(price);
			this.setPrice5Species(speciesId);
		}
		
		count++;
	}
	
	
	
	@Override
	public String toString() {
		return "RawPrice [id=" + id + ", dateId=" + dateId + ", provinceId="
				+ provinceId + ", cityId=" + cityId + ", areaId=" + areaId
				+ ", price1=" + price1 + ", price1Species=" + price1Species
				+ ", price2=" + price2 + ", price2Species=" + price2Species
				+ ", price3=" + price3 + ", price3Species=" + price3Species
				+ ", price4=" + price4 + ", price4Species=" + price4Species
				+ ", price5=" + price5 + ", price5Species=" + price5Species
				+ ", count=" + count + "]";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDateId() {
		return dateId;
	}
	public void setDateId(int dateId) {
		this.dateId = dateId;
	}
	public int getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public float getPrice1() {
		return price1;
	}
	private void setPrice1(float price1) {
		this.price1 = price1;
	}
	public int getPrice1Species() {
		return price1Species;
	}
	private void setPrice1Species(int price1Species) {
		this.price1Species = price1Species;
	}
	public float getPrice2() {
		return price2;
	}
	private void setPrice2(float price2) {
		this.price2 = price2;
	}
	public int getPrice2Species() {
		return price2Species;
	}
	private void setPrice2Species(int price2Species) {
		this.price2Species = price2Species;
	}
	public float getPrice3() {
		return price3;
	}
	private void setPrice3(float price3) {
		this.price3 = price3;
	}
	public int getPrice3Species() {
		return price3Species;
	}
	private void setPrice3Species(int price3Species) {
		this.price3Species = price3Species;
	}
	public float getPrice4() {
		return price4;
	}
	private void setPrice4(float price4) {
		this.price4 = price4;
	}
	public int getPrice4Species() {
		return price4Species;
	}
	private void setPrice4Species(int price4Species) {
		this.price4Species = price4Species;
	}
	public float getPrice5() {
		return price5;
	}
	private void setPrice5(float price5) {
		this.price5 = price5;
	}
	public int getPrice5Species() {
		return price5Species;
	}
	private void setPrice5Species(int price5Species) {
		this.price5Species = price5Species;
	}
}
