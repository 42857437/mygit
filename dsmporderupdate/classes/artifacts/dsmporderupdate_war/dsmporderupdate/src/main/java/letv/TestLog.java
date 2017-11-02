package letv;


import org.apache.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestLog
{

    static Logger loggerReceive;
    private static Object request;
    private String LogFileName;
    private String ModuleName;
    static Class class$com$TestLog;

    public TestLog()
    {
    }

    public TestLog(String path)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date currentTime_1 = new Date();
        String yearmonday = formatter.format(currentTime_1);
        LogFileName = path + "." + yearmonday + ".log";
    }

    public static void testLogold(String string)
    {
        new TestLog();
        loggerReceive.info(string);
    }

    public void SetModuleName(String name)
    {
        ModuleName = name;
    }

    public void testLog(String msg)
    {
        try
        {
            SimpleDateFormat formatter = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]");
            Date currentTime_1 = new Date();
            String currtime = formatter.format(currentTime_1);
            PrintWriter pw = new PrintWriter(new FileOutputStream(LogFileName, true));
            pw.println(currtime + "[" + ModuleName + "]" + msg);
            pw.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public static String[] splitMsg(String Text, int len)
	{
		String[] Str;
		int iMax = len;
		
		String eStr = "";
		if (Text.length() <= iMax)
		{
			Str = new String[1];
			Str[0] = Text;
		}
		else
		{
			int m = iMax - eStr.length();
			int c = (Text.length() + m - 1) / m;
			
			Str = new String[c];
			for (int i = 0; i < c; ++i)
			{
				if (i == c - 1)
					Str[i] = Text.substring(i * m);
				else
					Str[i] = Text.substring(i * m, i * m + m) + eStr;
			}
		}
		return Str;
	}
    
    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}