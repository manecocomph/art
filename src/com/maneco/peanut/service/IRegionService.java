package com.maneco.peanut.service;


public interface IRegionService {
	
	public String getCityDropdownOption(int provinceId);
	public String getAreaDropdownOption(int cityId);
	public String getProvinceDropdownOption(int countryId);
	
	public String who();
	public String why(String name);
	public int what(int age);
	public int when(int length);
	public String how(int height);
	public String whyNot();
	public void giveMe(String name);
}
