package com.bill99.mgw.test;

import com.bill99.mgw.entity.TransInfo;
import com.bill99.mgw.send.SendTR1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Test {
	public void test() {
		InputStream is = this.getClass().getResourceAsStream("/mgw.properties");
		String b = "";
		if (is != null) {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			try {
				while ((b = br.readLine()) != null) {
					System.out.println(b);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("kkkkk");
		}

	}

	public static void main(String args[]) {
		TransInfo.setPostUrl("https://mas.99bill.com:443/cnp/purchase");
		//返回组合前和返回TR2后的第一个标志字段
		TransInfo.setRecordeText_1("TxnMsgContent");
		//返回TR2后的错误标志字段
		TransInfo.setRecordeText_2("ErrorMsgContent");
		String xmltr1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\"><version>1.0</version><TxnMsgContent><txnType>PUR</txnType><interactiveStatus>TR1</interactiveStatus><cardNo>5528010000000001</cardNo><expiredDate>0911</expiredDate><cvv2>111</cvv2><amount>10.02</amount><merchantId>104110045116006</merchantId><terminalId>00006006</terminalId><entryTime>20090826125922</entryTime><externalRefNumber>20090826125923</externalRefNumber><customerId>13906003829</customerId><cardHolderName>卢利军</cardHolderName><cardHolderId>111</cardHolderId></TxnMsgContent></MasMessage>";
		SendTR1.sendTR1(xmltr1);
	}
}
