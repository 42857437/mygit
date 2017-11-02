package letv.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;



//数据库连接类
public class ConnJDBC
{
	//public static String SQLURL = "jdbc:oracle:thin:@220.181.153.48:1521:orcl";
	//获得连接
	public static Connection getConnection() 
	{
		InputStream is = null;
		Properties dbProps = null;
		String dbDriver =null;
		String dbURL =null;
		String dbUserName =null;
		String dbPassWord=null;
		try {
			dbProps = new Properties();
			is = ConnJDBC.class.getClassLoader().getResourceAsStream("db.properties");
			dbProps.load(is);
			dbDriver   = dbProps.getProperty("drivers").trim();
			dbURL      = dbProps.getProperty("dbURL").trim();
			dbUserName = dbProps.getProperty("userName").trim();
			dbPassWord = dbProps.getProperty("password").trim();
			System.out.println(dbDriver + "----" + dbURL + "----"+ dbUserName + "----" + dbPassWord);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("can't read property file,db.properties not in CLASSPATH!!");
			
		} finally {
			if (dbProps != null) {
				dbProps.clear();
				dbProps = null;
			}
	   }
		Connection conn = null;
		try
		{
				Class.forName(dbDriver);
				conn = DriverManager.getConnection(dbURL, dbUserName,dbPassWord);				
		}catch(Exception ex)
		{
			ex.printStackTrace();			
		}
		return conn;
	}
	
	public static void main(String[] args){
		
		System.out.println(SmsDAO.selectYYSproductid());	
	}
}
