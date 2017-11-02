package com.bill99.mgw.util;

import com.bill99.mgw.entity.TransInfo;

import java.util.HashMap;

/**
 * @Description: ��ǮVOP_CNP��Interface�ӿڳ���
 * @Copyright (c) �Ϻ���Ǯ��Ϣ�������޹�˾
 * @version 2.0
 */

/**
 * ��������ƴ��XML���ͽ���XML
 * */
//@SuppressWarnings("unchecked")
public class ParseUtil {
	/**
	 * �������XML����������һ��HashMap
	 * resXml����Ǯ���ص�XML������
	 * */
	public static HashMap parseXML(String resXml){
		HashMap returnRespXml=null;
		ParseXMLUtil pxu=ParseXMLUtil.initParseXMLUtil();//��ʼ��ParseXMLUtil
		if(resXml!=null){
			//�����ж�ѡ���Ǹ�������ȡXML����
			if(TransInfo.isFLAG()){
				returnRespXml= pxu.returnXMLData(pxu.parseXML(resXml), TransInfo.getRecordeText_1(), TransInfo.getRecordeText_2());
			}else{
				returnRespXml= pxu.returnXMLDataList(pxu.parseXML(resXml), TransInfo.getRecordeText_1(), TransInfo.getRecordeText_2());
			}
		}
		return returnRespXml;
	}
}
