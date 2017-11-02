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
	 * ����doPost()����
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}
	
	/**
	 * ������ϵͬ����Ϣ�ӿ�
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
	    TestLog       testlog  = new   TestLog(path); //��־��
	    testlog.SetModuleName("�ƶ�ͬ��������ϵ");
	    
		InputStream is = request.getInputStream();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] receiveBuffer = new byte[2048];
		int readBytesSize = is.read(receiveBuffer);
		while(readBytesSize != -1){
			bos.write(receiveBuffer, 0, readBytesSize);
			readBytesSize = is.read(receiveBuffer);
		}
		
		XMLData = new String(bos.toByteArray(), "UTF-8");
		System.out.println("OrderRelationReq��Ϣ��"+XMLData);
 	    testlog.testLog("[����"+request.getRemoteAddr()+"��DSMP������ϵ֪ͨ������Ϣ��OrderRelationReq]"+XMLData);
	    
//	    System.out.println("++++++++++++++++++++++++++++++++++++");
//	    System.out.println("���յ���ͬ����Ϣ����");
		System.out.println(XMLData);
//		System.out.println("++++++++++++++++++++++++++++++++++++");
	  //  ProvisionBean p = null;
		if(XMLData == null || XMLData.length() < 10 ){
			resultStr = false;   
		}else{  
			 System.out.println("00000000000000000");
//			p = new AnalysisXML().getXmlResultt(XMLData);
			 Map map = AnalysisXML.getXmlResult(XMLData);

			    //����ͬ����Ϣ
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
				 FeatureStr = map.get("FeatureStr").toString();   //��Чʱ�� BASE64����
				 LinkID = map.get("LinkID").toString();	 
				 	 
				//�ڶ�������װ��ͨ��Ա��ȡ����Ա�ӿ�URL�ַ���
		    	try{
		        	  if(SmsDAO.map.isEmpty()){
	              			 SmsDAO.selectYYSproductid();
	              		     System.out.println("selectYYSproductid is loading...");
	              		     testlog.testLog("[����productID:"+SPServiceID+"��dsmp������ϵ֪ͨ������Ϣ��]selectYYSproductid is loading...");
//	              		    logger.info("selectYYSproductid is loading...");
	              		 }
	              		 String   letvproductid = null;
	              		  if (SmsDAO.map.containsKey(SPServiceID)) {
//	              			  System.out.println("map.containsKey in productID:"+letvproductid);
	              			  letvproductid   = (String) SmsDAO.map.get(SPServiceID);
	               			
	              		  }else{
	              			  SmsDAO.map.clear();
//	              			  System.out.println("������map.containsKey not in productID:"+letvproductid);
	              			  SmsDAO.selectYYSproductid();
	              			  letvproductid   = (String) SmsDAO.map.get(SPServiceID);
	              		  }
		    		
		    	    String url ="";
		 		    long tm =System.currentTimeMillis();
		 		    /**
		 		     * DSMP����״̬�ж�·��,��ActionID��ֵ����
		 		     */
		 		 if(ActionID.equals("1")||ActionID.equals("3")){ //����
		         	 url ="http://ydnx.ott.letv.com/apf/common/internal/vipNew.shtml?mobile="+MSISDN+"&channel="+letvproductid+"&childChannel=&yysProductId="+SPServiceID+"&tm="+tm+"&streamingNo="+TransactionID+"&key="+MD5.MD5Crypt(String.valueOf(MSISDN)+tm+"8uyhl78ujjf67758090jfd2")+"&FeatureStr="+FeatureStr;
		         }else if(ActionID.equals("0")||ActionID.equals("4")||ActionID.equals("2")){ //�˶�
		         	 url ="http://ydnx.ott.letv.com/apf/unsubscribe/internal/vipNew.shtml?mobile="+MSISDN+"&channel="+letvproductid+"&childChannel=&yysProductId="+SPServiceID+"&tm="+tm+"&streamingNo="+TransactionID+"&key="+MD5.MD5Crypt(String.valueOf(MSISDN)+tm+"8uyhl78ujjf67758090jfd2")+"&FeatureStr="+FeatureStr;
		         }
		 		 
		 		 if(SPServiceID.equals("SPHYTY2")||SPServiceID.equals("SPHYTY3")||SPServiceID.equals("ktlsll")){ //�ض����²�Ʒ������Ҫ���봦��
		 	      try{
		 			    FeatureStr = new String(Base64Binrary.decodeBase64Binrary(FeatureStr));
		 			    testlog.testLog("�ֶ�FeatureStr Base64���뻹ԭ:"+FeatureStr);
		 		  }catch(Exception ex){
			 			ex.printStackTrace();
			 			testlog.testLog("�ֶ�FeatureStr Base64�����쳣:"+ex.getMessage());
			 	  }
		 		 }
		 		 //��������
		 		 thirdParter.Add2Vector(MsgType, TransactionID, Version, Send_Address, Dest_Address, Original_Address, MSISDN, MSISDN, LinkID, Aid, Areasonid, SPID, SPServiceID, accmode, FeatureStr, url);
		 			 		
		 		}catch(Exception ex2){
		 			ex2.printStackTrace();
		 			testlog.testLog("�ֶα������쳣:"+ex2.getMessage());
		 		}
		    	 
		    	//������,���͵����ж��������̣߳����߳̽��в������ݼ�ת����Ա��ͨȡ���ӿ�

		    	SendMsgThread.getInit();
			}
  
		PrintWriter out = response.getWriter();   
		if(resultStr){
			//���سɹ�
			resultXml = new AnalysisXML().getResultXml(TransactionID, Version, "0");   
		}else{
			//����ʧ��
			resultXml = new AnalysisXML().getResultXml(TransactionID, Version, "1");
		}
		testlog.testLog("[��ӦDSMP������ϵ֪ͨ������Ϣ��OrderRelationReq]"+resultXml);
		response.setContentType( "text/xml" ) ;
		out.print(resultXml);  
		out.flush();    
		out.close();  
	}
	
  }
