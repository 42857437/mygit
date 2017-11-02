package letv.send;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpWebUtil
{
	public static String sendHttp2Partner(String url,String data)
	{
		URL urlObject = null;
		HttpURLConnection con = null;
		InputStream in = null;
		BufferedReader reader = null;
		String content="";
		try
		{
			System.out.println("send httpRequest-->" + url);
			urlObject = new URL(url);
			con = (HttpURLConnection) urlObject.openConnection();
			con.setUseCaches(false);
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setConnectTimeout(2000); 
			con.setReadTimeout(2000);   
			con.connect();
			DataOutputStream dos = new DataOutputStream(con.getOutputStream());
			
			dos.write(data.getBytes());
			dos.flush();
			dos.close();
			in = con.getInputStream();
			reader = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while ((line = reader.readLine()) != null)
			{
				content += line;
			}
			System.out.println("send httpRequest return-->" + content);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if(reader != null)
					reader.close();
				in.close();
				con.disconnect();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
		}
		return content;
	}
	public static String sendHttp2Partner(String url)
	{
		URL urlObject = null;
		HttpURLConnection con = null;
		InputStream in = null;
		BufferedReader reader = null;
		String content="";
		try
		{
			System.out.println("send httpRequest-->" + url);
			urlObject = new URL(url);
			con = (HttpURLConnection) urlObject.openConnection();
			con.setUseCaches(false);
			con.setRequestMethod("GET");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setConnectTimeout(2000); 
			con.setReadTimeout(10000); 
			con.connect();
//			DataOutputStream dos = new DataOutputStream(con.getOutputStream());
//			dos.write(data.getBytes());
//			dos.flush();
//			dos.close();
			in = con.getInputStream();
			reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
			String line = "";
			while ((line = reader.readLine()) != null)
			{
				content += line;
			}
			System.out.println("send httpRequest return-->" + content);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			content = ex.getMessage();
		}
		finally
		{
			try
			{
				reader.close();
				in.close();
				con.disconnect();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
		}
		return content.trim();
	}
	
    
    /** 
     * ����HttpPost���� 
     *  
     * @param strURL 
     *            �����ַ 
     * @param params 
     *            json�ַ���,����: "{ \"id\":\"12345\" }" ;���������������˫����<br/> 
     * @return �ɹ�:����json�ַ���<br/> 
     */  
    public static String post(String strURL, String params) {  
        System.out.println(strURL);  
        System.out.println(params);  
        try {  
            URL url = new URL(strURL);// ��������  
            HttpURLConnection connection = (HttpURLConnection) url  
                    .openConnection();  
            connection.setDoOutput(true);  
            connection.setDoInput(true);  
            connection.setUseCaches(false);  
            connection.setInstanceFollowRedirects(true);  
            connection.setRequestMethod("POST"); // ��������ʽ  
            connection.setRequestProperty("Accept", "application/json"); // ���ý������ݵĸ�ʽ  
            connection.setRequestProperty("Content-Type", "application/json"); // ���÷������ݵĸ�ʽ  
            connection.connect();  
            OutputStreamWriter out = new OutputStreamWriter(  
                    connection.getOutputStream(), "UTF-8"); // utf-8����  
            out.append(params);  
            out.flush();  
            out.close();  
            // ��ȡ��Ӧ  
            int length = (int) connection.getContentLength();// ��ȡ����  
            InputStream is = connection.getInputStream();  
            if (length != -1) {  
                byte[] data = new byte[length];  
                byte[] temp = new byte[512];  
                int readLen = 0;  
                int destPos = 0;  
                while ((readLen = is.read(temp)) > 0) {  
                    System.arraycopy(temp, 0, data, destPos, readLen);  
                    destPos += readLen;  
                }  
                String result = new String(data, "UTF-8"); // utf-8����  
                System.out.println(result);  
                return result;  
            }  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return "error"; // �Զ��������Ϣ  
    } 
	public static void main(String[] args)
	{
//		String path = "C:\\0204MT.txt";
//		BufferedReader in = null;
//		String s = "";
//		try
//		{
//			in = new BufferedReader(new FileReader(path));
//			int i = 1;
//			while ((s = in.readLine()) != null)
//			{
//				HttpWebUtil.sendHttp2Partner(s);
//				System.out.println("" + i + "��");
//				i++;
//			}
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
		HttpWebUtil.sendHttp2Partner("http://eft.5151pay.com/GetDK.aspx","id=8BA3A032856D49459BEB76E4EDB8D0B3");
	}
}
