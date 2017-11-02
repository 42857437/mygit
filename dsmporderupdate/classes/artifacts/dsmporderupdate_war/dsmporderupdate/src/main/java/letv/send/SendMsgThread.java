package letv.send;

import letv.TestLog;
import letv.jdbc.SmsDAO;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.Vector;

public class SendMsgThread extends Thread
{
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(SendMsgThread.class); //.getName()
	@SuppressWarnings("unchecked")
	private Vector vector = new Vector();
	public static SendMsgThread sm = null;
	public static Connection con = null;

	public static SendMsgThread getInit()
	{
		if (sm == null)
		{
			synchronized (new Object())
			{
				sm = new SendMsgThread();
				new Thread(sm).start();
			}
		}
		return sm;
	}

	public SendMsgThread()
	{

	}
		@SuppressWarnings("unchecked")
		public void run()
		{
						
			//logger.log(null,"线程正在运行.........................................");
			
		    while(true){
		    	String        path  = "/letv/logs/dsmp/SendMsgThread.log";
		    	TestLog       testlog  = new   TestLog(path); //日志类		
			    testlog.SetModuleName("来自DSMP SendMsgThread订购关系路由通知");	
			try
			{
				vector = new Vector();
				if (thirdParter.vec.size() > 0)
				{
					synchronized (thirdParter.vec)
					{
						vector = new Vector(thirdParter.vec.size());
						for (int j = 0; j < thirdParter.vec.size(); j++)
						{
							vector.add(thirdParter.vec.get(j));
						}
						thirdParter.vec.clear();
					}
					System.out.println("clear datas:" + vector.size());
				}
				for (int k = 0; vector != null &&k < vector.size(); k++)
				{
					String ul="";
					String rp="";
					String ptcode="";
					@SuppressWarnings("unused")
					String ptmsg ="";
					String a1 = "";
				
//					JSONObject jsonObject2 =null;
					Msg mg = (Msg) vector.get(k);
				    ul=mg.url;
                   try{                    	
					     rp = HttpWebUtil.sendHttp2Partner(ul);					    
					     testlog.testLog("[来自SendMsgThread订购关系路由通知：]"+rp + "|| " + ul	);
					     a1 = java.net.URLDecoder.decode(new String(rp), "UTF-8");
//					     a1 = new String(rp.getBytes(),"UTF-8");
					     JSONObject jsonObject2 = JSONObject.fromObject(a1);
						 ptcode = jsonObject2.getString("code");
						 ptmsg  = jsonObject2.getString("msg");

                     }catch(Exception ex){
                    	 testlog.testLog("[来自SendMsgThread订购关系路由通知异常：]"+ul+ex.getMessage()) ;
                     }
                   
                     try{  
                    	    //SmsDAO insertSMSAction = new SmsDAO();
                    	 	SmsDAO.orderUpdate(mg.msgtype, mg.TransactionID, mg.Version, mg.Send_Address, mg.Dest_Address, mg.Original_Address, mg.FeeUser_ID, mg.DestUser_ID, mg.LinkID, mg.ActionID, mg.ActionReasonID, mg.SPID, mg.SPServiceID, mg.AccessMode,mg.FeatureStr, ul,ptcode);
                    		testlog.testLog("insert into BJYDDSMP(ID,msgtype,transactionid,version,send_address,dest_address,original_address,feeuser_id,destuser_id,linkid,actionid,actionreasonid,spid,spserviceid,accessmode,featurestr,url,ptcode) values(BJYDDSMP_SEQ.nextval,"+mg.msgtype+"," +
                    				""+mg.TransactionID+","+mg.Version+","+mg.Send_Address+","+mg.Dest_Address+","+mg.Original_Address+","+mg.FeeUser_ID+","+mg.DestUser_ID+","+mg.LinkID+","+ mg.ActionID+","+mg.ActionReasonID+","+mg.SPID+","+mg.SPServiceID+","+mg.AccessMode+","+mg.FeatureStr+","+ul+","+ptcode+")");
                     }catch(Exception ex){
                    	    System.out.println("insertSMSAction error:"+ex.getMessage()) ;
                    	    testlog.testLog("[来自SendMsgThread订购关系路由通知：insertSMSAction]"+ex.getMessage()) ;
                     }
                   
					mg = null;
		
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				vector.clear();
				vector = null;
			}
		    }
		}
		
		public static void main(String args[]) throws UnsupportedEncodingException{
			String ptcode="";
			String ptmsg ="";
			 String rp="{\"code\":\"-1\",\"msg\":\"ddddd\"}";
			 String   a1 = java.net.URLDecoder.decode(new String(rp), "UTF-8");
//			 String a1 = new String(rp.getBytes(),"UTF-8");
		     JSONObject jsonObject2 = JSONObject.fromObject(a1);
		     ptcode = jsonObject2.getString("code");
			 ptmsg  = jsonObject2.getString("msg");
			System.out.println(ptcode);
			System.out.println(ptmsg);
		}
		
}
