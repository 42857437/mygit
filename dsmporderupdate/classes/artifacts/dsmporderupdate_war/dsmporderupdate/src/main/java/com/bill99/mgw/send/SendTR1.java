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
 * @Description: 快钱VOP_CNP的Interface接口程序
 * @Copyright (c) 上海快钱信息服务有限公司
 * @version 2.0
 */

/**
 * 该类用来拼接XML串和解析XML
 * */
//@SuppressWarnings("unchecked")
public class SendTR1 {
	/**
	 * 改方法用于正常的交易拼接XML，返回StringBuffer
	 * sb：初始StringBuffer
	 * paraName：XML中的标签名
	 * paraValue：XML中标签对应的值
	 **/
	public static StringBuffer appendParam(StringBuffer reqXml,String paraName,String paraValue){
		//如果输入的值为null,那么使它们的值为""
		if(paraValue==null){
			paraValue="";
		}
		//下面开始组合XML
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
	 * 提交TR1信息，并且获取TR2
	 * */
	public static HashMap sendTR1(String tr1XML){
		System.out.println("TR1信息："+tr1XML);
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
			System.out.println("读取配置文件出错!");
			e.printStackTrace();
		}
		
		//初始化服务器类
		PostTr1Processor ptp =  new PostTr1ProcessorImpl();
		//得到服务端返回
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
			System.out.println("TR2信息："+reqData);
			if(is!=null){
				respXml= ParseUtil.parseXML(reqData);//给解析XML的函数传递快钱返回的XML数据流
			}
		} catch (Exception e) {
			System.out.println("发送TR1失败");
			e.printStackTrace();
		}
		return respXml;
	}
}
