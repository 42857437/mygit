
package com.log;

import org.apache.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static void main(String args[])
    {
        String path = "D:/5252";
        TestLog logger = new TestLog(path);
        logger.testLog("okokok1");
        logger.testLog("okokok2");
        logger.testLog("okokok3");
        logger.testLog("okokok1");
        logger.testLog("okokok2");
    }
}
