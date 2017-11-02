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
	//用于验签的公钥文件名
	private String certFile = null;

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
	 * 该方法用来接收TR3和输出TR4
	 */
	//@SuppressWarnings({ "unused", "unchecked" })
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//设置请求信息的字符编码
		request.setCharacterEncoding("utf-8");
				
		//设置输出形式和输出字符编码
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
		System.out.println("TR3信息："+reqData);
		String            path         = "/letv/logs/ivr/ReceiveTR3ToTR4.java";
		TestLog       testlog      = new   msg.TestLog(path); //日志类
		testlog.SetModuleName("快钱手机语音IVR支付TR3信息");
		String RemoteAddr  = request.getRemoteAddr();     
		testlog.testLog("[来自IP:"+RemoteAddr+"的请求TR3信息：]"+reqData);
		if(!SignUtil.veriSignForXml(reqData, certFile)) {
			throw new RuntimeException("can not sign data!");
		}else{
			//返回TR3后的第一个标志字段
			TransInfo.setRecordeText_1("TxnMsgContent");
			//返回TR3后的错误标志字段
			TransInfo.setRecordeText_2("ErrorMsgContent");
			//设置最后的解析方式
			TransInfo.setFLAG(true);
	
			/***************开始接收TR3**************/
			//将获取的数据传入DOM解析函数中
			HashMap respXml=ParseUtil.parseXML(reqData);
			if(respXml!=null){
	
				//接口版本号（version）
				String version=(String)respXml.get("version");
						  
				//交易类型编码（txnType）
				String txnType=(String)respXml.get("txnType");
		
				//消息状态（interactiveStatus）
				String interactiveStatus=(String)respXml.get("interactiveStatus");
						  
				//交易金额（amount）
				String amount=(String)respXml.get("amount");
				
				//商户编号		  
				String merchantId=(String)respXml.get("merchantId");
	
				//商户编号		  
				String settleMerchantId=(String)respXml.get("settleMerchantId");
						  
				//终端编号（terminalId）
				String terminalId=(String)respXml.get("terminalId");
						  
				//外部检索参考号（externalRefNumber）
				String externalRefNumber=(String)respXml.get("externalRefNumber");
						  
				//客户号（customerId）
				String customerId=(String)respXml.get("customerId");
						  
				//检索参考号（refNumber）
				String refNumber=(String)respXml.get("refNumber");
						  
				//应答码（responseCode）
				String responseCode=(String)respXml.get("responseCode");
		
				//应答文本信息（responseTextMessage）
		//		String responseTextMessage=(String)respXml.get("responseTextMessage");
						  
				//交易传输时间（transTime）
				String transTime=(String)respXml.get("transTime");
						  
				//客户端交易时间（entryTime）
				String entryTime=(String)respXml.get("entryTime");
						  
				//发卡组织编号（cardOrg）
				String cardOrg=(String)respXml.get("cardOrg");
						  
				//发卡银行名称（issuer）
				String issuer=(String)respXml.get("issuer");
							
				//缩略卡号（storableCardNo）
				String storableCardNo=(String)respXml.get("storableCardNo");
						  
				//授权码（authorizationCode）	
				String authorizationCode=(String)respXml.get("authorizationCode");
							
				//报文数字签名（signature）
				String signature=(String)respXml.get("signature");
		
				/********TR3接收完毕*********************************/
				List lList = OperatorDB.queryivrorder(externalRefNumber);
				Iterator iter = lList.iterator();
				Cnporder co = null;
				if (lList != null) {
					while (iter.hasNext()) {
						    co = (Cnporder) iter.next();
					        System.out.println(co.getMerid() + "  " + co.getMercallbackurl() + "  " + co.getMerOrderid());
					        testlog.testLog("[IVR TR3通知数据：]"+co.getMerid() + "  " + co.getMercallbackurl() + "  " + co.getMerOrderid());
					        //插入CNP支付结果通知库
					        boolean isok=false;
					        isok = OperatorDB.Insertivrcallback((String)respXml.get("version"), (String)respXml.get("interactiveStatus"), (String)respXml.get("txnType"), (String)respXml.get("amount"), (String)respXml.get("merchantId"), (String)respXml.get("terminalId"), (String)respXml.get("refNumber"), (String)respXml.get("externalRefNumber"), (String)respXml.get("customerId"),
							co.getMerid(), co.getMercallbackurl(), co.getMerOrderid(), (String)respXml.get("responseCode"), (String)respXml.get("transTime"), (String)respXml.get("entryTime"), (String)respXml.get("issuer"));
					        testlog.testLog("[IVR TR3通知入库数据Insertivrcallback状态：]"+isok);
					}
				}	
				
				//当应答码responseCode的值为00时，交易成功
				if("00".equals(responseCode)){
					/***************************************************************
					 * 进行数据库的逻辑操作，比如更新数据库或插入记录。
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
					 System.out.println("IVR TR3通知响应码："+responsestr +"请求地址："+co.getMercallbackurl()+sendstr);
					 testlog.testLog("[IVR TR3通知响应码：]"+responsestr +"请求地址："+co.getMercallbackurl()+sendstr);
				}
				/********输出TR4************************************/
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
				System.out.println("TR4信息："+tr4XML.toString());
				testlog.testLog("[TR4信息：]"+tr4XML.toString());
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
			System.out.println("publickey加载出错");
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
