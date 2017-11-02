package dsmp;

import com.bill99.mgw.util.Base64Binrary;
import com.log.TestLog;
import letv.MD5;
import letv.jdbc.SmsDAO;
import letv.send.SendMsgThread;
import letv.send.thirdParter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

/*
@
 */

public class OrderRelationReq extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -20099233233222221L;

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * 调用doPost()方法
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}
	
	/**
	 * 订购关系同步消息接口
	 * @throws IOException 
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String resultXml = "";
		boolean resultStr = true;
		String XMLData = null;
		String TransactionID = null;
		String Version =null;
		String MsgType =null;
		String MSISDN =null;
		String ActionID ="0";
		String ActionReasonID =null;
		String SPID =null;
		String SPServiceID =null;
		String AccessMode =null;
		String FeatureStr = null;
		String LinkID =null;
		String Send_Address ="0001";
		String Dest_Address ="0";
		String Original_Address="0";
		int accmode=0;
		int Areasonid=0;
		int Aid=0;
		String        path     = "/letv/logs/dsmp/OrderRelationReq";
	    TestLog       testlog  = new   TestLog(path); //日志类
	    testlog.SetModuleName("移动同步订购关系");
	    
		InputStream is = request.getInputStream();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] receiveBuffer = new byte[2048];
		int readBytesSize = is.read(receiveBuffer);
		while(readBytesSize != -1){
			bos.write(receiveBuffer, 0, readBytesSize);
			readBytesSize = is.read(receiveBuffer);
		}
		
		XMLData = new String(bos.toByteArray(), "UTF-8");
		System.out.println("OrderRelationReq信息："+XMLData);
 	    testlog.testLog("[来自"+request.getRemoteAddr()+"的DSMP订购关系通知请求信息：OrderRelationReq]"+XMLData);
	    
//	    System.out.println("++++++++++++++++++++++++++++++++++++");
//	    System.out.println("接收到的同步消息数据");
		System.out.println(XMLData);
//		System.out.println("++++++++++++++++++++++++++++++++++++");
	  //  ProvisionBean p = null;
		if(XMLData == null || XMLData.length() < 10 ){
			resultStr = false;   
		}else{  
			 System.out.println("00000000000000000");
//			p = new AnalysisXML().getXmlResultt(XMLData);
			 Map map = AnalysisXML.getXmlResult(XMLData);

			    //处理同步消息
//				for (Object key : map.keySet()) {  
//			     System.out.println( key + "= " + map.get(key));  
//				} 			
				 TransactionID = map.get("TransactionID").toString();
				 Version = map.get("Version").toString();
				 MsgType = map.get("MsgType").toString();
				 MSISDN = map.get("MSISDN").toString();
				 ActionID = map.get("ActionID").toString();
				 Aid=Integer.parseInt(ActionID);
				 ActionReasonID = map.get("ActionReasonID").toString();
				 Areasonid=Integer.parseInt(ActionReasonID);
				 SPID = map.get("SPID").toString();
				 SPServiceID = map.get("SPServiceID").toString();
				 AccessMode = map.get("AccessMode").toString();
				  accmode=Integer.parseInt(AccessMode);
				 FeatureStr = map.get("FeatureStr").toString();   //生效时间 BASE64编码
				 LinkID = map.get("LinkID").toString();	 
				 	 
				//第二步，组装开通会员、取消会员接口URL字符串
		    	try{
		        	  if(SmsDAO.map.isEmpty()){
	              			 SmsDAO.selectYYSproductid();
	              		     System.out.println("selectYYSproductid is loading...");
	              		     testlog.testLog("[来自productID:"+SPServiceID+"的dsmp订购关系通知请求信息：]selectYYSproductid is loading...");
//	              		    logger.info("selectYYSproductid is loading...");
	              		 }
	              		 String   letvproductid = null;
	              		  if (SmsDAO.map.containsKey(SPServiceID)) {
//	              			  System.out.println("map.containsKey in productID:"+letvproductid);
	              			  letvproductid   = (String) SmsDAO.map.get(SPServiceID);
	               			
	              		  }else{
	              			  SmsDAO.map.clear();
//	              			  System.out.println("到这了map.containsKey not in productID:"+letvproductid);
	              			  SmsDAO.selectYYSproductid();
	              			  letvproductid   = (String) SmsDAO.map.get(SPServiceID);
	              		  }
		    		
		    	    String url ="";
		 		    long tm =System.currentTimeMillis();
		 		    /**
		 		     * DSMP订购状态判断路由,由ActionID的值决定
		 		     */
		 		 if(ActionID.equals("1")||ActionID.equals("3")){ //订购
		         	 url ="http://ydnx.ott.letv.com/apf/common/internal/vipNew.shtml?mobile="+MSISDN+"&channel="+letvproductid+"&childChannel=&yysProductId="+SPServiceID+"&tm="+tm+"&streamingNo="+TransactionID+"&key="+MD5.MD5Crypt(String.valueOf(MSISDN)+tm+"8uyhl78ujjf67758090jfd2")+"&FeatureStr="+FeatureStr;
		         }else if(ActionID.equals("0")||ActionID.equals("4")||ActionID.equals("2")){ //退定
		         	 url ="http://ydnx.ott.letv.com/apf/unsubscribe/internal/vipNew.shtml?mobile="+MSISDN+"&channel="+letvproductid+"&childChannel=&yysProductId="+SPServiceID+"&tm="+tm+"&streamingNo="+TransactionID+"&key="+MD5.MD5Crypt(String.valueOf(MSISDN)+tm+"8uyhl78ujjf67758090jfd2")+"&FeatureStr="+FeatureStr;
		         }
		 		 
		 		 if(SPServiceID.equals("SPHYTY2")||SPServiceID.equals("SPHYTY3")||SPServiceID.equals("ktlsll")){ //特定包月产品代码需要解码处理
		 	      try{
		 			    FeatureStr = new String(Base64Binrary.decodeBase64Binrary(FeatureStr));
		 			    testlog.testLog("字段FeatureStr Base64编码还原:"+FeatureStr);
		 		  }catch(Exception ex){
			 			ex.printStackTrace();
			 			testlog.testLog("字段FeatureStr Base64编码异常:"+ex.getMessage());
			 	  }
		 		 }
		 		 //保存数据
		 		 thirdParter.Add2Vector(MsgType, TransactionID, Version, Send_Address, Dest_Address, Original_Address, MSISDN, MSISDN, LinkID, Aid, Areasonid, SPID, SPServiceID, accmode, FeatureStr, url);
		 			 		
		 		}catch(Exception ex2){
		 			ex2.printStackTrace();
		 			testlog.testLog("字段编码存放异常:"+ex2.getMessage());
		 		}
		    	 
		    	//第三步,发送到队列对象，启用线程，由线程进行操作数据及转发会员开通取消接口

		    	SendMsgThread.getInit();
			}
  
		PrintWriter out = response.getWriter();   
		if(resultStr){
			//返回成功
			resultXml = new AnalysisXML().getResultXml(TransactionID, Version, "0");   
		}else{
			//返回失败
			resultXml = new AnalysisXML().getResultXml(TransactionID, Version, "1");
		}
		testlog.testLog("[响应DSMP订购关系通知请求信息：OrderRelationReq]"+resultXml);
		response.setContentType( "text/xml" ) ;
		out.print(resultXml);  
		out.flush();    
		out.close();  
	}
	
  }
