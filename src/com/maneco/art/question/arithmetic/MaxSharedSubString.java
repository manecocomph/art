package com.maneco.art.question.arithmetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  问题来源:　http://www.iteye.com/topic/1118325
 *  面试题目：求N个字符串中的最大公子串，比如： 
 *  1、abcde 
 *  2、abefg 
 *  3、werabc 
 *  这三个字符串的最大公子串是ab 
 * @author Eric.Tian
 */
public class MaxSharedSubString {

	public static void main(String[] args) {
		
		ArrayList<String> strings = new ArrayList<String>(Arrays.asList("abcdefsdfababcghy", "abcdeftdsfabcdefsdf", "abcdefsdf", "abcdabcdefsdfdg", "abcdabcdefsdfdgabcdabcdefsdfdgabcdabcdefsdfdg"));
		//ArrayList<String> strings = new ArrayList<String>(Arrays.asList("abcabc", "cabcfffffff"));
		long start = System.currentTimeMillis();
		System.out.println(MaxSharedSubString.getMaxSharedSubString(strings));
		System.out.println("Cost: " + (System.currentTimeMillis() - start));
	}
	
	public static String getMaxSharedSubString(List<String> strs) {
		// 以最短字符串为基准去测试
		String shortestStr = MaxSharedSubString.getShortestStr(strs);
		
		String maxSharedSubStr = "";
		int j = 0;
		String tmpStr = "";
		// 最短字符串的每个字符开头的字符
		for (int i = 0; i < shortestStr.length(); i++) {
			// 从以这个字符开头的最长字符子串开始, 逐渐缩短, 但不能短于已经找到的最短串
			for (j = shortestStr.length(); j > i & (j - i) > maxSharedSubStr.length(); j--) {
				tmpStr = shortestStr.substring(i, j);
				// Check 一下是不是都含有这个子串
				if (MaxSharedSubString.ifAllHave(strs, tmpStr)) {
					maxSharedSubStr = tmpStr;
				}
			}
		}
		
		return maxSharedSubStr;
	}
	
	private static String getShortestStr(List<String> strs) {
		String tmpStr = strs.get(0);
		
		for (String str : strs) {
			if (str.length() > tmpStr.length()) {
				tmpStr = str;
			}
		}
		
		return tmpStr;
	}
	
	private static boolean ifAllHave(List<String> strs, String string) {
		for (String str : strs) {
			if (!str.contains(string)) {
				return false;
			}
		}
		
		return true;
	}
}
