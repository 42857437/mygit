package letv.jdbc;

import letv.send.SendMessageEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class SmsDAO 
{

	
	  //�����յ��Ķ������ݲ������ݿ�
	  public static void insertNotifySms(String notifMessage,String senderAddress,String linkId,String productId,String spId,String spPassword)
	  {
		  Connection conn = ConnJDBC.getConnection();
		  PreparedStatement pstmt = null;
		  try
		{
			String sql = "insert into notifMessage values(?,?,?,?,?,?)" ;			  
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, notifMessage);
			pstmt.setString(2, senderAddress);
			pstmt.setString(3, linkId);
			pstmt.setString(4, productId);
			pstmt.setString(5, spId);
			pstmt.setString(6, spPassword);
			pstmt.execute();
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(pstmt!=null)
			{
				try
				{
					pstmt.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
						
			if(conn!=null)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}			
		}
		  
	  }
	  
	  //���Ͷ��ŷ��ص�״̬����������ݿ�
	  public void insertDelivery(String correlator,String address, String deliveryStatus)
	  {
		  Connection conn = ConnJDBC.getConnection();
		  PreparedStatement pstmt = null;
		  try
		{
			String sql = "insert into deliveryStatus values(?,?,?)" ;  
			  
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, correlator);
			pstmt.setString(2, address);
			pstmt.setString(3, deliveryStatus);
			
			pstmt.execute();
			
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(pstmt!=null)
			{
				try
				{
					pstmt.close();
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			if(conn!=null)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		  
	  }
	  
	  //ִ��ҵ��֪ͨ��Ϣ
	  public void  serviceConsumeNotify(String streamingNo,String productID,String userID,int userIdType,String linkID,String featureStr)
	  {
		
		  
		  Connection conn = ConnJDBC.getConnection();
		  PreparedStatement pstmt = null;
		  try
		{
			String sql = "insert into serviceConsume values(?,?,?,?,?,?)" ;  
			  
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, streamingNo);
			pstmt.setString(2, productID);
	        pstmt.setString(3, userID);
			pstmt.setInt(4, userIdType);
			pstmt.setString(5, linkID);
			pstmt.setString(6, featureStr);
			pstmt.execute();
			
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(pstmt!=null)
			{
				try
				{
					pstmt.close();
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			if(conn!=null)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		  
	  }
	  //������Ϣ������Ϣ�������ݿ�
	  public static void  orderUpdate(String msgtype,String transactionid,String version,String send_address,String dest_address,String 
			  original_address,String feeuser_id,String destuser_id,String linkid,Integer actionid,Integer actionreasonid,String 
			  spid,String spserviceid, Integer accessmode,String featurestr,String url,String ptcode)
	  {
		  System.out.println("orderUpdate....") ;
		  Connection conn = ConnJDBC.getConnection();
		  PreparedStatement pstmt = null;
	try
		{
		 String sql = "";
		if(spid.equals("901681")){
		        sql = "insert into BJYDDSMP(ID,msgtype,transactionid,version,send_address,dest_address,original_address,feeuser_id,destuser_id,linkid,actionid,actionreasonid,spid,spserviceid,accessmode,featurestr,url,ptcode,servicedate) values(BJYDDSMP_SEQ.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'))" ; 
		}else{
			    sql = "insert into BJYDDSMP(ID,msgtype,transactionid,version,send_address,dest_address,original_address,feeuser_id,destuser_id,linkid,actionid,actionreasonid,spid,spserviceid,accessmode,featurestr,url,ptcode) values(BJYDDSMP_SEQ.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		}
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, msgtype);
			pstmt.setString(2, transactionid);
			pstmt.setString(3, version);
			pstmt.setString(4, send_address);
			pstmt.setString(5, dest_address);
			pstmt.setString(6, original_address);
			pstmt.setString(7, feeuser_id);
			pstmt.setString(8, destuser_id);
			pstmt.setString(9, linkid);		
			pstmt.setInt(10, actionid);
			pstmt.setInt(11, actionreasonid);
			pstmt.setString(12, spid);
			pstmt.setString(13, spserviceid);
			pstmt.setInt(14, accessmode);
			pstmt.setString(15, featurestr);
			pstmt.setString(16, url);
			pstmt.setString(17, ptcode);
			if(spid.equals("901681")){
			pstmt.setString(18, featurestr);
			}
			
			pstmt.execute();
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(pstmt!=null)
			{
				try
				{
					pstmt.close();
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			if(conn!=null)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		  
	  }
	  
	  
	  
	  //������Ϣ���´������ݿ�
	  public void  notifyManagementInfo(String streamingNo,String ID,int status,int IDType)
	  {
				 
		  
		  Connection conn = ConnJDBC.getConnection();
		  PreparedStatement pstmt = null;
		try
		{
			String sql = "insert into notifyManagementInfo values(?,?,?,?)" ;  
			  
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, streamingNo);
			pstmt.setString(2, ID);
			pstmt.setInt(3, status);
			pstmt.setInt(4, IDType);
			
			pstmt.execute();
			
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(pstmt!=null)
			{
				try
				{
					pstmt.close();
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			if(conn!=null)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		  
	  }
	  //��ѯ��������
	  public SendMessageEntity selectSms()
	  {

		  Connection conn = ConnJDBC.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		  SendMessageEntity sendMessageEntity = null;
			try
			{
				String sql = "select  * from sendSms where status = 0 order by insertDate " ;  
				  
				pstmt = conn.prepareStatement(sql);
			
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					sendMessageEntity = new SendMessageEntity();
					sendMessageEntity.setSmsID(rs.getInt("SMSID"));
					sendMessageEntity.setAddresse(rs.getString("addresses"));
					sendMessageEntity.setCharging(rs.getString("charging"));
					sendMessageEntity.setFeecode(rs.getString("feecode"));
					sendMessageEntity.setLinkId(rs.getString("linkId"));
					sendMessageEntity.setMessage(rs.getString("message"));
					sendMessageEntity.setProductId(rs.getString("productId"));
					sendMessageEntity.setSAN(rs.getString("SAN"));
					sendMessageEntity.setSenderAddress(rs.getString("senderAddress"));
					sendMessageEntity.setSmsURL(rs.getString("smsURL"));
					sendMessageEntity.setSpId(rs.getString("spId"));
					sendMessageEntity.setSpPassword(rs.getString("spPassword"));
				}
				
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				if(rs!=null)
				{
					try
					{
						rs.close();
					}
					catch (SQLException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(pstmt!=null)
				{
					try
					{
						pstmt.close();
					}
					catch (SQLException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
				if(conn!=null)
				{
					try
					{
						conn.close();
					}
					catch (SQLException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		  
		  return sendMessageEntity;
	  }
	  //�޸Ķ���״̬
	  public void updateStatus(int smsID)
	  {
		  Connection conn = ConnJDBC.getConnection();
			PreparedStatement pstmt = null;
		
			try
			{
				String sql = "update sendSms set status = 1 where SMSID = ?" ;  
				  
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, smsID);
				pstmt.execute();
				
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				if(pstmt!=null)
				{
					try
					{
						pstmt.close();
					}
					catch (SQLException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
				if(conn!=null)
				{
					try
					{
						conn.close();
					}
					catch (SQLException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		  
		  
	  }
	  @SuppressWarnings("unchecked")
	public static Map map = new HashMap<String, String>();
	  //��ѯ��Ʒ��Ϣ
	  @SuppressWarnings("unchecked")
	public  static  boolean selectYYSproductid()
	  {

		    Connection conn = ConnJDBC.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			boolean  isok =false;			
		 
			try
			{
				String sql = "select  * from yysproduct " ;  				  
				pstmt = conn.prepareStatement(sql);			
				rs = pstmt.executeQuery();
				while(rs.next())
				{					
					map.put(rs.getString("yysproductid"),rs.getString("letvproductid"));
					System.out.println(rs.getString("yysproductid"));
				}
				 isok =true;
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				isok =false;
			}
			finally
			{
				if(rs!=null)
				{
					try
					{
						rs.close();
					}
					catch (SQLException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(pstmt!=null)
				{
					try
					{
						pstmt.close();
					}
					catch (SQLException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
				if(conn!=null)
				{
					try
					{
						conn.close();
					}
					catch (SQLException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		  
		  return isok;
	  }
	  
	
}
