package com.tianxiaohui.art.aop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PerformanceRecorder {
	private static List<PerformanceUnit> pfmsList = new ArrayList<PerformanceUnit>(1024);
	private static int SIZE_LIMIT = 1020;
	private static String PERFORMANCE_FILE = "performance.log";
	private static String SEPRATOR = ";";
	
	public static void record(PerformanceUnit pfmcUnit) {
		if (null != pfmcUnit) {
			pfmsList.add(pfmcUnit);
			
			if (pfmsList.size() > SIZE_LIMIT) {
				// write to databaase or file system
				flushToFile();
			}
		}
	}
	
	public static void flushToFile() {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(new File(PERFORMANCE_FILE), true));
			for (PerformanceUnit pu : pfmsList) {
				bw.append(pu.signature + SEPRATOR + pu.elapseTime + "\n");
			}
			
			pfmsList.clear();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != bw) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
