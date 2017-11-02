package com.bill99.mgw.test;

import com.bill99.mgw.util.SignUtil;

import java.net.URL;


public class SignTest {
	public static void main(String[] args){
		SignTest si = new SignTest();
		boolean fl =  si.test();
		System.out.println(fl);
			
	}
	
	
	public boolean  test() {
		URL url = this.getClass().getResource("mgw.cer");
		String tr3Xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\"><version>1.0</version><TxnMsgContent><txnType>PRE</txnType><interactiveStatus>TR3</interactiveStatus><amount>10.21</amount><merchantId>104110045112025</merchantId><terminalId>00002025</terminalId><entryTime>20090610133808</entryTime><externalRefNumber>20090622093154</externalRefNumber><transTime>20090620093335</transTime><refNumber>000000003628</refNumber><responseCode>00</responseCode><cardOrg>CU</cardOrg><storableCardNo>5183780005</storableCardNo><authorizationCode>615307</authorizationCode><signature>aiXjjjwlSIbyoVOsvldRX3FtRxOiS98LTWapPkcxvrXKD5GqqZmitLHSC5VT2/zo4WZIQF7DnHvIP9j/FYCsLnqZ/RjdE4+mVbixqlHDXjPSx17bDWXhCgG7xpxSTgJLiOYg8BWDPOTzjkxZwRwdcPhNLOhR6ug29MqOV3jX2Nk=</signature></TxnMsgContent></MasMessage>";
		
		return SignUtil.veriSignForXml(tr3Xml,url.getFile());
	}
}
