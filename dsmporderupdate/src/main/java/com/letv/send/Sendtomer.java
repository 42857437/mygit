package com.letv.send;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Sendtomer {
	
	
	public static   String   sendtomer(String  sendUrl){
	String currentLine = "";
	String content = "";
	
	HttpURLConnection l_connection = null;
	InputStream l_urlStream = null;
	BufferedReader l_reader = null;
	try {
		URL l_url = new URL(sendUrl);
		l_connection = (HttpURLConnection) l_url.openConnection();
		l_connection.setConnectTimeout(3000);
		l_connection.connect();
		//l_connection.getConnectTimeout();
		
		l_urlStream = l_connection.getInputStream();
		l_reader = new BufferedReader(new InputStreamReader(
				l_urlStream, "utf-8"));
		while ((currentLine = l_reader.readLine()) != null) {
			content += currentLine;
		}
		
	} catch (Exception exx) {
	System.out.println("___ doingB ..."+exx.getMessage());
		
	} finally {
		try {
			if (l_reader != null)
				l_reader.close();
			if (l_urlStream != null)
				l_urlStream.close();
			if (l_connection != null)
				l_connection.disconnect();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
		
	       return content;
	}

	 public  static void main(String args[]){
		 String str ="ÂÞ³ÐÔ£";
		 String encode_utf = "";
			try {	
			
				 encode_utf = URLEncoder.encode(str,"UTF-8");
				 System.out.println(encode_utf);
				// encode_utf="%E7%BD%91%E9%85%92%E7%BD%91";
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 //String urlstr="https://lepay.letv.com:8443/testssl/testservlet?test=2&name="+encode_utf;
		 String urlstr="http://lepay.letv.com/LetvPayzx/bdebitcon.do?responseCode=00&merid=300863&merOrderid=20130322215111123513065&amount=490&issuer=ÕÐÉÌÒøÐÐ&transTime=20130322215111&externalRefNumber=20130322215111&Lun=null&Lcn=9555500100827228&LCHID=110115197205037128&cellphone=13521936978";
		 System.out.println(sendtomer(urlstr));
		 
	 }
	
	
}
