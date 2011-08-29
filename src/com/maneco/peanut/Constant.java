package com.maneco.peanut;

import java.text.SimpleDateFormat;

public final class Constant {
	private Constant() {
	}

	public static final String UNIT_CATTY = "元/斤";
	public static final String UNIT_TON = "元/吨";
	public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
	
	public static final int COMMON_SPECIES = 0;
	
	public static final String CACHE_DOMAIN_PRODUCT = "product";
	public static final String CACHE_DOMAIN_SPECIES = "species";
}
