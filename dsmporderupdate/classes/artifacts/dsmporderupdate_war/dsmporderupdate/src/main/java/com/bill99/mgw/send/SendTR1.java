package com.bill99.mgw.send;

import com.bill99.mgw.core.PostTr1Processor;
import com.bill99.mgw.core.PostTr1ProcessorImpl;
import com.bill99.mgw.entity.MerchantInfo;
import com.bill99.mgw.entity.TransInfo;
import com.bill99.mgw.util.ParseUtil;
import com.bill99.mgw.util.PropFile;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 * @Description: ��ǮVOP_CNP��Interface�ӿڳ���
 * @Copyright (c) �Ϻ���Ǯ��Ϣ�������޹�˾
 * @version 2.0
 */

/**
 * ��������ƴ��XML���ͽ���XML
 * */
//@SuppressWarnings("unchecked")
public class SendTR1 {
	/**
	 * �ķ������������Ľ���ƴ��XML������StringBuffer
	 * sb����ʼStringBuffer
	 * paraName��XML�еı�ǩ��
	 * paraValue��XML�б�ǩ��Ӧ��ֵ
	 **/
	public static StringBuffer appendParam(StringBuffer reqXml,String paraName,String paraValue){
		//��������ֵΪnull,��ôʹ���ǵ�ֵΪ""
		if(paraValue==null){
			paraValue="";
		}
		//���濪ʼ���XML
		if(reqXml==null){
			reqXml=new StringBuffer();
			String contentXML="<?xml version=\"1.0\" encoding=\"UTF-8\"?><MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\">";
			reqXml.append(contentXML).append("<").append(paraName).append(">").append(paraValue).append("</").append(paraName).append(">").append("<").append(TransInfo.getRecordeText_1()).append(">");
		}else if((!"".equals(paraName)) && (!"".equals(paraValue))){
			reqXml.append("<").append(paraName).append(">").append(paraValue).append("</").append(paraName).append(">");
		}else if(("".equals(paraName)) && ("".equals(paraValue))){
			reqXml.append("</").append(TransInfo.getRecordeText_1()).append(">").append("</MasMessage>");
		}
		
		return reqXml;
	}
	
	/**
	 * �ύTR1��Ϣ�����һ�ȡTR2
	 * */
	public static HashMap sendTR1(String tr1XML){
		System.out.println("TR1��Ϣ��"+tr1XML);
		MerchantInfo merchantInfo=null;
		try {
			Properties p=PropFile.getProps("/mgw.properties");
			merchantInfo=new MerchantInfo();
			merchantInfo.setCertPass(p.getProperty("certPassword"));
			merchantInfo.setCertPath(p.getProperty("certFileName"));
			merchantInfo.setMerchantId(p.getProperty("merchantId"));
			merchantInfo.setPassword(p.getProperty("merchantLoginKey"));
			merchantInfo.setTimeOut(p.getProperty("timeout"));
			merchantInfo.setDomainName(p.getProperty("domainName"));
			merchantInfo.setSslPort(p.getProperty("sslPort"));
			merchantInfo.setUrl(TransInfo.getPostUrl());
			merchantInfo.setXml(tr1XML);
			
		} catch (Exception e) {
			System.out.println("��ȡ�����ļ�����!");
			e.printStackTrace();
		}
		
		//��ʼ����������
		PostTr1Processor ptp =  new PostTr1ProcessorImpl();
		//�õ�����˷���
		HashMap respXml = null;
		try {
			InputStream is = ptp.post(merchantInfo);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] receiveBuffer = new byte[2048];
			int readBytesSize = is.read(receiveBuffer);
			while(readBytesSize != -1){
				bos.write(receiveBuffer, 0, readBytesSize);
				readBytesSize = is.read(receiveBuffer);
			}
			String reqData = new String(bos.toByteArray(), "UTF-8");
			System.out.println("TR2��Ϣ��"+reqData);
			if(is!=null){
				respXml= ParseUtil.parseXML(reqData);//������XML�ĺ������ݿ�Ǯ���ص�XML������
			}
		} catch (Exception e) {
			System.out.println("����TR1ʧ��");
			e.printStackTrace();
		}
		return respXml;
	}
}
