package com.bill99.mgw.core;

import com.bill99.mgw.entity.MerchantInfo;
import com.bill99.mgw.util.Base64Binrary;
import com.bill99.mgw.util.MyX509TrustManager;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.KeyStore;

/**
 * @project mgwCore
 * @description:数据发送实现
 * @author cen
 * @create_time:Jun 22, 2009
 * @modify_time:Jun 22, 2009
 */
public class PostTr1ProcessorImpl implements PostTr1Processor {

	public InputStream post(MerchantInfo merchantInfo) throws Exception {
		// 验证密钥源
		File certFile = new File(merchantInfo.getCertPath());
		KeyStore ks = KeyStore.getInstance("JKS");
		ks.load(new FileInputStream(certFile), merchantInfo.getCertPass().toCharArray());
		KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		kmf.init(ks, merchantInfo.getCertPass().toCharArray());

		// 同位体验证信任决策源
		TrustManager[] tm = { new MyX509TrustManager() };

		// 初始化安全套接字
		SSLContext sslContext = SSLContext.getInstance("SSL");
		sslContext.init(kmf.getKeyManagers(), tm, null);

		SSLSocketFactory factory = sslContext.getSocketFactory();
		
		System.out.println();

		//"https://mas.99bill.com/cnp/purchase" port:443
		URL url = new URL(merchantInfo.getUrl());
		HttpsURLConnection urlc = (HttpsURLConnection) url.openConnection();
		urlc.setSSLSocketFactory(factory);
		urlc.setDoOutput(true);
		urlc.setDoInput(true);
		urlc.setReadTimeout(merchantInfo.getTimeOut() * 1000);

		String authString = merchantInfo.getMerchantId() + ":" + merchantInfo.getPassword();
		
		String auth = "Basic " + Base64Binrary.encodeBase64Binrary(authString.getBytes());
		
		urlc.setRequestProperty("Authorization", auth);

		OutputStream out = urlc.getOutputStream();
		out.write(merchantInfo.getXml().getBytes("UTF-8"));
		
		out.flush();
		out.close();

		return urlc.getInputStream();
	}
}
