package com.bill99.mgw.entity;

public class TransInfo {
	private static String postUrl;			//�ύ��ַ
	
	private static boolean FLAG;		//�����ж�ѡ�õĽ�������
	
	private static String recordeText_1;		//������¼XML���ݸ�ʽ�ֶΣ�������¼XML��һ����־���ݸ�ʽ�ֶ�
	
	private static String recordeText_2;		//����ǵ����ݶ�ʱ��������¼XML�ڶ�����־���ݸ�ʽ�ֶ�

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
