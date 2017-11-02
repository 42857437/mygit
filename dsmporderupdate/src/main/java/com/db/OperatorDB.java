package com.db;


import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;


public class OperatorDB 
{	
	public static void initProp() {
		InputStream is = null;
		Properties dbProps = null;
		try {
			dbProps = new Properties();
			is = OperatorDB.class.getClassLoader().getResourceAsStream("db.properties");
			dbProps.load(is);
			Db.dbDriver   = dbProps.getProperty("drivers").trim();
			Db.dbURL      = dbProps.getProperty("dbURL").trim();
			Db.dbUserName = dbProps.getProperty("userName").trim();
			Db.dbPassWord = dbProps.getProperty("password").trim();
//			System.out.println(Db.dbDriver + "----" + Db.dbURL + "----"+ Db.dbUserName + "----" + Db.dbPassWord);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("can't read property file,db.properties not in CLASSPATH!!");
			return;
		} finally {
			if (dbProps != null) {
				dbProps.clear();
				dbProps = null;
			}
		}
	}
	
	/*
	 * Insert 数据到信用卡CNP订单表
	 * 
	 */
	public static   boolean  Insertivrorder(String version,String interactiveStatus, String txnType,String  amount,String merchantId,String terminalId,
			String cardHolderName,String cellphone,String customerId,String merid,String mercallbackurl,String merOrderid,String externalRefNumber)
	{
		Connection conn = null;
		Statement  stat = null;
		ResultSet  rs   = null;
		String     sql  = null;		
	    PreparedStatement pstmt = null;

		sql = "insert into ivr_order(id,version,interactiveStatus,txnType,amount,merchantId,terminalId,cardHolderName,cellphone,customerId,merid," +
				"mercallbackurl,merOrderid,externalRefNumber)" +
			"  "+" values(IVR_ORDER_SEQ.nextval,'"+version+"','"+interactiveStatus+"','"+txnType+"','"+amount+"','"+merchantId+"','"+terminalId+"','"+cardHolderName+"','"+cellphone+"','"+customerId+"','"+merid+"','"+mercallbackurl+"','"+merOrderid+"','"+externalRefNumber+"')";
		try {
			   
			   if (conn == null || conn.isClosed()) {
				    OperatorDB.initProp(); 
				    conn = Db.getCn();
			   }		
			        pstmt = conn.prepareStatement(sql);
			        pstmt.executeUpdate();
			
		} catch (SQLException ex) {
			    ex.printStackTrace();
			    return false;
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (stat != null) {
					stat.close();
					stat = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Insert 数据到信用卡CNP成功表
	 * 
	 */
	public static   boolean  Insertivrcallback(String version,String interactiveStatus, String txnType,String  amount,String merchantId,String terminalId,String refNumber,String externalRefNumber,String customerId,
			String merid,String mercallbackurl,String merOrderid,String responseCode,String transTime,String entryTime,String issuer)
	{
		Connection conn = null;
		Statement  stat = null;
		ResultSet  rs   = null;
		String     sql  = null;		
	    PreparedStatement pstmt = null;

		sql = "insert into ivr_callback(id,version,interactiveStatus,txnType,amount,merchantId,terminalId,refNumber,externalRefNumber,customerId," +
				"merid,mercallbackurl,merOrderid,responseCode,transTime,entryTime,issuer)" +
			"  "+" values(IVR_CALLBACK_SEQ.nextval,'"+version+"','"+interactiveStatus+"','"+txnType+"','"+amount+"','"+merchantId+"','"+terminalId+"','"+refNumber+"','"+externalRefNumber+"','"+customerId+"','"+merid+"','"+mercallbackurl+"','"+merOrderid+"'," +
					"'"+responseCode+"','"+transTime+"','" +entryTime+"','"+issuer+"')";
		try {
			   if (conn == null || conn.isClosed()) {
				   OperatorDB.initProp();
				    conn = Db.getCn();
			   }		
			   pstmt = conn.prepareStatement(sql);
			   pstmt.executeUpdate();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (stat != null) {
					stat.close();
					stat = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	public static List queryivrorder(String externalRefNumber) {
		
		Connection conn = null;
		Statement  stat = null;
		ResultSet  rs   = null;
		String     sql  = null;	
		List       list = null; 
	    PreparedStatement pstmt = null;

		sql = "select merid,mercallbackurl,merOrderid  from  ivr_order where externalRefNumber=?";
		try {
			
			   if (conn == null || conn.isClosed()) {
				    OperatorDB.initProp(); 
				    conn = Db.getCn();
			   }		
			        pstmt = conn.prepareStatement(sql);
			        pstmt.setString(1, externalRefNumber);
			        rs = pstmt.executeQuery();
			        if(rs.next()){
			        	Cnporder  co = new Cnporder();
			        	list         = new ArrayList();
			        	co.setMerid(rs.getString("merid"));
			        	co.setMercallbackurl(rs.getString("mercallbackurl"));
			        	co.setMerOrderid(rs.getString("merOrderid"));
			        	list.add(co);			        	
			        }			
		} catch (SQLException ex) {
			    ex.printStackTrace();			    
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (stat != null) {
					stat.close();
					stat = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				
			}
		}
	     	return list;	
	}
	

	public static void main(String args[]) {
		OperatorDB.initProp();
//		Insertcnporder(String version,String interactiveStatus, String txnType,String  amount,String merchantId,String terminalId,
//				String cardHolderName,String customerId,String merid,String mercallbackurl,String merOrderid)
		System.out.println(Insertivrorder("1.0","TR1","PUR","10","104110045116006","00006006","卢利军","13520250363","13906003829","1","http://vip.letv.com",
				"201001133333","1234567890123456789012"));
		
//		Insertcnpcallback(String version,String interactiveStatus, String txnType,String  amount,String merchantId,String terminalId,String refNumber,String externalRefNumber,String customerId,
//				String merid,String mercallbackurl,String merOrderid,String responseCode,String transTime,String entryTime,String issuer)
//		System.out.print(Insertcnpcallback("1.0","TR2","PUR","10","104110045116006","00006006","02222222",
//				"201107260434","00006006","1","http://vip.letv.com","201000113333","00","2011080910111","20110811090212","建设银行"));		 
	List lList = OperatorDB.queryivrorder("1234567890123456789012");
	Iterator iter = lList.iterator();
	Cnporder co = null;
	if (lList != null) {
		while (iter.hasNext()) {
			 co = (Cnporder) iter.next();
		     System.out.println(co.getMerid() + "  " + co.getMercallbackurl()
					+ "  " + co.getMerOrderid());
		}
	}
	}
}
