package msg;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendURL
{

	public SendURL()
	{
	}

	public static String sendURL(URL url) throws IOException
	{
		BufferedReader in = null;
		StringBuffer strBuffer = null;
		String str = "";

		try
		{
			strBuffer = new StringBuffer();
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			while ((str = in.readLine()) != null)
				strBuffer.append(str);
		}
		catch (RuntimeException e)
		{
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		finally
		{
			if (in != null)
			{
				in.close();
				in = null;
			}
		}

		return strBuffer.toString();
	}

	public static String senUrl(String sb) throws IOException
	{
		DataInputStream din = null;
		HttpURLConnection con = null;
		String reStr=null;
		try
		{
			URL urlObject = new URL(sb.toString());
			con = (HttpURLConnection) urlObject.openConnection();
			//con.setConnectTimeout(30000);
			con.setRequestMethod("GET");
			con.setDoOutput(true);
			din = new DataInputStream(urlObject.openStream());
			byte[] aa = new byte[1024];
			
			din.readFully(aa, 0, aa.length);
			System.out.println(new String(aa));
			reStr = new String(aa);
			din.close();
		}
		catch (Exception ex)
		{
			 if(con!=null){
				 con.disconnect();
				 con=null;
			 }
			 if(din!=null){
				 din.close();
				 din=null;
			 }

		}finally{
			
		}

		return  reStr;

	}

	{
	}
}