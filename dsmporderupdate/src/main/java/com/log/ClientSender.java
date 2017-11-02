package com.log;


import javax.net.ssl.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author letv
 */
public class ClientSender {

    private final static String HTTPS_TRANSMIT_LOCATION = "HTTPS Transmitter";
    //重写X509TrustManager类的三个方法,信任服务器证书
    private X509TrustManager xtm = new X509TrustManager() {

        public void checkClientTrusted(X509Certificate[] chain, String authType) {
            try {
                throw new Exception();
            } catch (Exception ex) {
                Logger.getLogger(ClientSender.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    };
    //信任主机
    private HostnameVerifier hnv = new HostnameVerifier() {

        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };
    //构造初始化SSLContext

    public ClientSender() throws Exception {

        SSLContext sslContext = null;

        try {
            //
         /*  下单接口只针对网酒网因此，验证密钥源,这里可以不验
            File certFile = new File("");
            KeyStore ks = KeyStore.getInstance("JKS");
            ks.load(new FileInputStream(certFile), "".toCharArray());
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(ks, "".toCharArray());*/
            sslContext = SSLContext.getInstance("TLS");
            X509TrustManager[] xtmArray = new X509TrustManager[]{xtm};

            sslContext.init(null, xtmArray, new java.security.SecureRandom());
        } catch (GeneralSecurityException gse) {
            System.out.println("Https:DataSender:General Security Exception .");
            gse.getStackTrace();
        }

        if (sslContext != null) {
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        }

        HttpsURLConnection.setDefaultHostnameVerifier(hnv);
    }

    /**
     *  通过HTTPS发送消息
     */
    public int sendMessage(String url, byte[] Msg) throws MalformedURLException, IOException {
        //System.setProperty("javax.net.debug","true");
        URL remoteHostURL;
        HttpsURLConnection httpsUrlConnection = null;

        //建立连接
        try {
            remoteHostURL = new URL(url);
            // System.out.println( ct.getRemoteHostURI());
            httpsUrlConnection = (HttpsURLConnection) remoteHostURL.openConnection();
        } catch (Exception _ex) {
            System.out.println("Unable to openConnection on URL.");
            _ex.printStackTrace();
        }

        //  HttpsURLConnection.setFollowRedirects(true);
        // httpsUrlConnection.setInstanceFollowRedirects(true);

        httpsUrlConnection.setDoOutput(true);
        httpsUrlConnection.setDoInput(true);

        httpsUrlConnection.setRequestMethod("POST");

        httpsUrlConnection.setRequestProperty("Content-length", Integer.toString(Msg.length));
       // httpsUrlConnection.setRequestProperty("Content-type", "application/x-xxxxxxxx; version=1.0");
        httpsUrlConnection.setRequestProperty("Authorization", "basic " + "dGNsb3VkYWRtaW46dGNsb3VkMTIz");

        httpsUrlConnection.connect();

        OutputStream outStream = null;
        try {
            outStream = httpsUrlConnection.getOutputStream();
            outStream.write(Msg);
            outStream.flush();
        } finally {
            if (outStream != null) {
                outStream.close();
            }
        }

        int responseCode = httpsUrlConnection.getResponseCode();
        InputStream is = httpsUrlConnection.getInputStream();
        /**输入流转码成GBK*/
        BufferedReader in = new BufferedReader(new InputStreamReader(is, "GBK"));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        System.out.println("返回的数据：" + buffer.toString());
        /**得到返回数据*/
        /**返回数据样例：*/
        String response = buffer.toString();
        httpsUrlConnection.disconnect();
        System.out.println("Https_DataSender:" + responseCode);
        return responseCode;
    }

    public static void main(String[] args) throws Exception {
        String st = "deptno=133&gateway=http%3A%2F%2F116.236.253.92%3A8080%2FshortcutPayment%2Ftrade.html%3F&merchantId=421&inputCharset=UTF-8&returnUrl=http%3A%2F%2F10.129.222.65%3A8080%2Fpw_shortcut%2Freturn_url.jsp&notifyUrl=http%3A%2F%2F10.129.222.65%3A8080%2Fpw_shortcut%2Fnotify_url.jsp&merchantTradeId=CA2999&goodsTitle=%E6%94%AF%E4%BB%98%E6%B5%8B%E8%AF%95&amountFee=0.11&paymentName=%E7%BD%97%E6%89%BF%E8%A3%95&paymentPhone=13810810043&cardType=0&paymentIdType=01&payCard=ICBC&paymentId=140104196507061355&cardNo=6210981000007556014&cardPwd=300104&period=&cvv=&paymentCode=&sign=6b11f54954f411f820919b01bac66327";
        byte[] send = st.getBytes("UTF-8");
        String url = "  https://lepay.letv.com/LetvPayzx/pw_shortcut.do";//https://lepay.letv.com:8443/testssl/testservlet     https://localhost:8443/testssl/testservlet
      
        try {
            new ClientSender().sendMessage(url, send);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClientSender.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
