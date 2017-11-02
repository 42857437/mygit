package com.bill99.mgw.entity;

public class TransInfo {
	private static String postUrl;			//提交地址
	
	private static boolean FLAG;		//用来判断选用的解析函数
	
	private static String recordeText_1;		//用来记录XML内容格式字段，用来记录XML第一个标志内容格式字段
	
	private static String recordeText_2;		//当标记的内容多时，用来记录XML第二个标志内容格式字段

	public static String getPostUrl() {
		return postUrl;
	}

	public static void setPostUrl(String postUrl) {
		TransInfo.postUrl = postUrl;
	}

	public static boolean isFLAG() {
		return FLAG;
	}

	public static void setFLAG(boolean flag) {
		FLAG = flag;
	}

	public static String getRecordeText_1() {
		return recordeText_1;
	}

	public static void setRecordeText_1(String recordeText_1) {
		TransInfo.recordeText_1 = recordeText_1;
	}

	public static String getRecordeText_2() {
		return recordeText_2;
	}

	public static void setRecordeText_2(String recordeText_2) {
		TransInfo.recordeText_2 = recordeText_2;
	}
}
