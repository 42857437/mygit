package com.bill99.mgw.receive;

import com.bill99.mgw.entity.TransInfo;
import com.bill99.mgw.util.ParseUtil;
import com.bill99.mgw.util.PropFile;
import com.bill99.mgw.util.SignUtil;
import com.db.Cnporder;
import com.db.OperatorDB;
import com.letv.send.Sendtomer;
import msg.TestLog;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
public class ReceiveTR3ToTR4 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//������ǩ�Ĺ�Կ�ļ���
	private String certFile = null;

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
	 * �÷�����������TR3�����TR4
	 */
	//@SuppressWarnings({ "unused", "unchecked" })
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//����������Ϣ���ַ�����
		request.setCharacterEncoding("utf-8");
				
		//���������ʽ������ַ�����
		response.setContentType("text/xml");
		response.setCharacterEncoding("utf-8");
		
		InputStream is = request.getInputStream();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] receiveBuffer = new byte[2048];
		int readBytesSize = is.read(receiveBuffer);
		while(readBytesSize != -1){
			bos.write(receiveBuffer, 0, readBytesSize);
			readBytesSize = is.read(receiveBuffer);
		}
		
		String reqData = new String(bos.toByteArray(), "UTF-8");
		System.out.println("TR3��Ϣ��"+reqData);
		String            path         = "/letv/logs/ivr/ReceiveTR3ToTR4.java";
		TestLog       testlog      = new   msg.TestLog(path); //��־��
		testlog.SetModuleName("��Ǯ�ֻ�����IVR֧��TR3��Ϣ");
		String RemoteAddr  = request.getRemoteAddr();     
		testlog.testLog("[����IP:"+RemoteAddr+"������TR3��Ϣ��]"+reqData);
		if(!SignUtil.veriSignForXml(reqData, certFile)) {
			throw new RuntimeException("can not sign data!");
		}else{
			//����TR3��ĵ�һ����־�ֶ�
			TransInfo.setRecordeText_1("TxnMsgContent");
			//����TR3��Ĵ����־�ֶ�
			TransInfo.setRecordeText_2("ErrorMsgContent");
			//�������Ľ�����ʽ
			TransInfo.setFLAG(true);
	
			/***************��ʼ����TR3**************/
			//����ȡ�����ݴ���DOM����������
			HashMap respXml=ParseUtil.parseXML(reqData);
			if(respXml!=null){
	
				//�ӿڰ汾�ţ�version��
				String version=(String)respXml.get("version");
						  
				//�������ͱ��루txnType��
				String txnType=(String)respXml.get("txnType");
		
				//��Ϣ״̬��interactiveStatus��
				String interactiveStatus=(String)respXml.get("interactiveStatus");
						  
				//���׽�amount��
				String amount=(String)respXml.get("amount");
				
				//�̻����		  
				String merchantId=(String)respXml.get("merchantId");
	
				//�̻����		  
				String settleMerchantId=(String)respXml.get("settleMerchantId");
						  
				//�ն˱�ţ�terminalId��
				String terminalId=(String)respXml.get("terminalId");
						  
				//�ⲿ�����ο��ţ�externalRefNumber��
				String externalRefNumber=(String)respXml.get("externalRefNumber");
						  
				//�ͻ��ţ�customerId��
				String customerId=(String)respXml.get("customerId");
						  
				//�����ο��ţ�refNumber��
				String refNumber=(String)respXml.get("refNumber");
						  
				//Ӧ���루responseCode��
				String responseCode=(String)respXml.get("responseCode");
		
				//Ӧ���ı���Ϣ��responseTextMessage��
		//		String responseTextMessage=(String)respXml.get("responseTextMessage");
						  
				//���״���ʱ�䣨transTime��
				String transTime=(String)respXml.get("transTime");
						  
				//�ͻ��˽���ʱ�䣨entryTime��
				String entryTime=(String)respXml.get("entryTime");
						  
				//������֯��ţ�cardOrg��
				String cardOrg=(String)respXml.get("cardOrg");
						  
				//�����������ƣ�issuer��
				String issuer=(String)respXml.get("issuer");
							
				//���Կ��ţ�storableCardNo��
				String storableCardNo=(String)respXml.get("storableCardNo");
						  
				//��Ȩ�루authorizationCode��	
				String authorizationCode=(String)respXml.get("authorizationCode");
							
				//��������ǩ����signature��
				String signature=(String)respXml.get("signature");
		
				/********TR3�������*********************************/
				List lList = OperatorDB.queryivrorder(externalRefNumber);
				Iterator iter = lList.iterator();
				Cnporder co = null;
				if (lList != null) {
					while (iter.hasNext()) {
						    co = (Cnporder) iter.next();
					        System.out.println(co.getMerid() + "  " + co.getMercallbackurl() + "  " + co.getMerOrderid());
					        testlog.testLog("[IVR TR3֪ͨ���ݣ�]"+co.getMerid() + "  " + co.getMercallbackurl() + "  " + co.getMerOrderid());
					        //����CNP֧�����֪ͨ��
					        boolean isok=false;
					        isok = OperatorDB.Insertivrcallback((String)respXml.get("version"), (String)respXml.get("interactiveStatus"), (String)respXml.get("txnType"), (String)respXml.get("amount"), (String)respXml.get("merchantId"), (String)respXml.get("terminalId"), (String)respXml.get("refNumber"), (String)respXml.get("externalRefNumber"), (String)respXml.get("customerId"),
							co.getMerid(), co.getMercallbackurl(), co.getMerOrderid(), (String)respXml.get("responseCode"), (String)respXml.get("transTime"), (String)respXml.get("entryTime"), (String)respXml.get("issuer"));
					        testlog.testLog("[IVR TR3֪ͨ�������Insertivrcallback״̬��]"+isok);
					}
				}	
				
				//��Ӧ����responseCode��ֵΪ00ʱ�����׳ɹ�
				if("00".equals(responseCode)){
					/***************************************************************
					 * �������ݿ���߼�����������������ݿ������¼��
					 **************************************************************/
				      String  KEY="LETV151.182.94.134PAY";  
					  String  signMsgmerval = "";  
					  String  mersignMsgval="";
					  String sendstr="";
//						signMsgmerval = appendParam(signMsgmerval, "responseCode", (String)respXml.get("responseCode"));
//						signMsgmerval = appendParam(signMsgmerval, "merid", co.getMerid());
//						signMsgmerval = appendParam(signMsgmerval, "merOrderid", co.getMerOrderid());
//						signMsgmerval = appendParam(signMsgmerval, "amount", (String)respXml.get("amount"));
//						signMsgmerval = appendParam(signMsgmerval, "issuer", (String)respXml.get("issuer"));
//						signMsgmerval = appendParam(signMsgmerval, "status", "10");
//						signMsgmerval = appendParam(signMsgmerval, "transTime", (String)respXml.get("transTime"));
//						signMsgmerval = appendParam(signMsgmerval, "externalRefNumber", externalRefNumber);		
//						signMsgmerval = appendParam(signMsgmerval, "key", KEY);
//						 mersignMsgval = MD5Util.md5Hex(signMsgmerval.getBytes("gb2312")).toUpperCase();
					 sendstr="?responseCode="+(String)respXml.get("responseCode")+"&merid="+co.getMerid()+"&merOrderid="+co.getMerOrderid()+"&amount="+(String)respXml.get("amount")+"&issuer="+(String)respXml.get("issuer")+"&transTime="+ (String)respXml.get("transTime")+"&externalRefNumber="+externalRefNumber+"&sign="+mersignMsgval; 	
					 String responsestr="";
					 responsestr =  Sendtomer.sendtomer(co.getMercallbackurl()+sendstr);
					 System.out.println("IVR TR3֪ͨ��Ӧ�룺"+responsestr +"�����ַ��"+co.getMercallbackurl()+sendstr);
					 testlog.testLog("[IVR TR3֪ͨ��Ӧ�룺]"+responsestr +"�����ַ��"+co.getMercallbackurl()+sendstr);
				}
				/********���TR4************************************/
				StringBuffer tr4XML=new StringBuffer();//<version>1.0</version>
				tr4XML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\"><TxnMsgContent>");
				tr4XML.append("<txnType>").append(txnType).append("</txnType>");
				tr4XML.append("<interactiveStatus>TR4</interactiveStatus>");
				tr4XML.append("<merchantId>").append(merchantId).append("</merchantId>");
				if(settleMerchantId!=null && "".equals(settleMerchantId)){
					tr4XML.append("<settleMerchantId>").append(settleMerchantId).append("</settleMerchantId>");
				}
				tr4XML.append("<terminalId>").append(terminalId).append("</terminalId>");
				tr4XML.append("<refNumber>").append(refNumber).append("</refNumber>");
				tr4XML.append("</TxnMsgContent></MasMessage>");
				System.out.println("TR4��Ϣ��"+tr4XML.toString());
				testlog.testLog("[TR4��Ϣ��]"+tr4XML.toString());
				response.getOutputStream().write(new String(tr4XML).getBytes("UTF-8"));
			}
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	//@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			certFile = PropFile.getProps("/mgw.properties").getProperty("publickey");
		} catch (Exception e) {
			System.out.println("publickey���س���");
			e.printStackTrace();
		}
	}
	
	
//	public String appendParam(String returnStr, String paramId,
//			String paramValue) {
//		if (!returnStr.equals("")) {
//			if (!paramValue.equals("")) {
//				returnStr = returnStr + "&" + paramId + "=" + paramValue;
//			}
//		} else {
//			if (!paramValue.equals("")) {
//				returnStr = paramId + "=" + paramValue;
//			}
//		}
//		return returnStr;
//	}

	

	
}
