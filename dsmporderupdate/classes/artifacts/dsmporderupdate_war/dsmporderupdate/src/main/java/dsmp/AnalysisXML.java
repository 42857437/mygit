package dsmp;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

import java.io.File;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.StringReader;
import java.util.*;

/**
 * 
 * @author Administrator
 *
 */

public class AnalysisXML {
	/**
	 * �������ص�XML��Ϣ
	 * @param XMLData 
	 */
	@SuppressWarnings("unchecked")
	public static ProvisionBean getXmlResultt(String XMLData) {
		ProvisionBean p = new ProvisionBean();
		File f = null;  
		try{
//			org.jdom.input.SAXBuilder sb=new org.jdom.input.SAXBuilder();
			org.jdom.Document doc=null;
			 System.out.println("111111111111111111111111111");
//			File ffFile = new File("/letv/logs/dsmp");
//			if(!ffFile.exists()){
//				ffFile.mkdir();
//			}
//			 System.out.println("22222222222222222222222222");
////			f = new File("/letv/logs/dsmp/"+System.currentTimeMillis()+new Random().nextInt(100000)+".xml");
//			f = new File("/letv/logs/dsmp/1323232dd.xml");
//			if(f.exists()){
//				f.delete();  
//			}   
//			 System.out.println("44444444444444444444444444");
//			f.createNewFile();    
//			RandomAccessFile ra = new RandomAccessFile(f,"rw");
//			ra.writeBytes(XMLData);
//			ra.close();   
			
//			doc=sb.build(f);//��ȡPROVISION.XML�ļ���SB��DOC��ʹ�÷������Ǻ����ף�
			 
			 try {  
				    SAXBuilder xmlBuilder = new SAXBuilder();  
				    Reader reader = new StringReader(XMLData);  
				    doc = xmlBuilder.build(reader);  
				    System.out.println("Root: " + doc.getRootElement().getName());  
				} catch (JDOMException je) {  
				    je.printStackTrace();  
				} 
//			 System.out.println("5555555555555555555555555555"+doc);
	//		�����Ƕ�ȡPROVISION.XML����Ϣ������XML�ļ��ĸ����ֶ��壬�Ͷ�ȡ��ֵ�������
		    Element root=doc.getRootElement();
//			System.out.println("Element root:"+XMLData);
//			org.jdom.Element root= new Element(XMLData);  

			//��ȡheader��Ϣ��
//		    try{
//		    }catch(JDOMException je){
//		    	je.printStackTrace();
//		    
//		    }
			Element header=(Element)XPath.selectSingleNode(root,"/SOAP-ENV:Envelope/SOAP-ENV:Header");
			List ns=header.getChildren();
			for(Iterator i=ns.iterator();i.hasNext();){
				Object n=i.next();
				if(n instanceof Element){
					Element e=(Element)n;
					if(e.getName().equals("TransactionID")){
						 System.out.println("6666666666666666"+e.getTextNormalize());
						p.setTransactionID(e.getTextNormalize());
					}
				}
			}
		   
			//��ȡbody��Ϣ��
			Element SyncOrderRelationReq = null ;
			Element body=(Element)XPath.selectSingleNode(root,"/SOAP-ENV:Envelope/SOAP-ENV:Body");
			ns=body.getChildren();
			for(Iterator i=ns.iterator();i.hasNext();){
				Object n=i.next();
				if(n instanceof Element){
					Element e=(Element)n;
					if(e.getName().equals("SyncOrderRelationReq")){
						SyncOrderRelationReq = e;
					}
				}
			}
		  
			//ȡ�����ݰ������к��ӡ�
			if(SyncOrderRelationReq!=null){
				ns=SyncOrderRelationReq.getChildren();
				for(Iterator i=ns.iterator();i.hasNext();){
					Object n=i.next();
					if(n instanceof Element){
						Element e=(Element)n;  
						//------------------------------------
						if(e.getName().equals("FeeUser_ID")){
							ns = e.getChildren();
							for(Iterator j=ns.iterator();j.hasNext();){
								Object k=j.next();
								if(k instanceof Element){
									Element eU=(Element)k;  
									if(eU.getName().equals("MSISDN")){
										p.setMsisdnu(eU.getText());
									}
								}     
							}
						}
						if(e.getName().equals("DestUser_ID")){
							ns = e.getChildren();
							for(Iterator j=ns.iterator();j.hasNext();){
								Object k=j.next();
								if(k instanceof Element){
									Element eU=(Element)k;  
									if(eU.getName().equals("MSISDN")){
										p.setMSISDN_D(eU.getText());
									}
								}    
							}
						}
//						//------------------------------------
						if(e.getName().equals("Version")){
							p.setVersion(e.getTextNormalize());
						}
						if(e.getName().equals("MsgType")){
							p.setMsgType(e.getTextNormalize());
						}
						if(e.getName().equals("LinkID")){
							p.setLinkID(e.getTextNormalize());
						}
						if(e.getName().equals("ActionID")){
							p.setActionID(e.getTextNormalize());
						}
						if(e.getName().equals("ActionReasonID")){
							p.setActionReasonID(e.getTextNormalize());
						}
						if(e.getName().equals("SPID")){
							p.setSPID(e.getTextNormalize());
						}
						if(e.getName().equals("SPServiceID")){
							p.setSPServiceID(e.getTextNormalize());
						}
						if(e.getName().equals("FeatureStr")){
							p.setFeatureStr(e.getTextNormalize());
						}
					}
				}
			}
		
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ͬ��MISC���󣬽�������!");
		}finally{
			if(f!=null){
				f.delete();
			}
		}
		return p;
	}

	/** 
     * �������ص�XML��Ϣ 
     * @param xml  
     * @return 0Ϊ�ɹ�������Ϊʧ�� 
     */  
    @SuppressWarnings("unchecked")
	public static Map<String,String> getXmlResult(String result) {  
        File f = null;  
        Map<String, String> map = new HashMap<String, String>();  
        try{  
            f = new File("/letv/logs/dsmp/temp"+System.currentTimeMillis()+new Random().nextInt(100000)+".xml");  
            if(f.exists()){  
                f.delete();  
            }   
            f.createNewFile();  
            RandomAccessFile ra = new RandomAccessFile(f,"rw");  
            ra.writeBytes(result);  
            ra.close();    
            SAXBuilder builder = new SAXBuilder();   
            Document doc = builder.build(f);   
            Element foo = doc.getRootElement();     
            List allChildren = foo.getChildren(); //��ȡ���нڵ㼯��  
            for (int i = 0; i < allChildren.size(); i++) {   
                Element e = ((Element) allChildren.get(i));  
                String nName = e.getName();  
                //System.out.println(nName);    
                if(nName.equals("Header")){  
                    List hList = e.getChildren(); //��ȡHeader�ڵ㼯��  
                    for (int j = 0; j < hList.size(); j++) {  
                        Element eH = ((Element) hList.get(j));  
                        //System.out.println(eH.getName()+"---"+eH.getText());   
                        map.put(eH.getName(), eH.getText());  
                        System.out.println(eH.getName()+"="+eH.getText());
                    }   
                }else{    
                    List bList = e.getChildren(); //��ȡBody�ڵ㼯��  
                    for (int j = 0; j < bList.size(); j++) {  
                        Element eB = ((Element) bList.get(j));  
                        //System.out.println(eB.getName()+"---"+eB.getContent().size());   
                        map.put(eB.getName(), eB.getContent().size()+"");  
                        List bEList = eB.getChildren();  
                        for (int k = 0; k < bEList.size(); k++) {  
                            Element eBE = ((Element) bEList.get(k));  
                            //System.out.println(eBE.getName()+"---"+eBE.getText());  
                            
                        	//------------------------------------
    						if(eBE.getName().equals("FeeUser_ID")){
    						 List	ns = eBE.getChildren();
    							for(Iterator g=ns.iterator();g.hasNext();){
    								Object h=g.next();
    								if(h instanceof Element){
    									Element eU=(Element)h;  
    									if(eU.getName().equals("MSISDN")){
    										  map.put(eU.getName(),eU.getText()); 
    										  System.out.println(eU.getName()+"="+eU.getText());
    									}
    								}     
    							}
    						}
    						if(eBE.getName().equals("DestUser_ID")){
    							List ns = e.getChildren();
    							for(Iterator g=ns.iterator();g.hasNext();){
    								Object h=g.next();
    								if(h instanceof Element){
    									Element eU=(Element)h;  
    									if(eU.getName().equals("MSISDN")){
    										 map.put(eU.getName(), eU.getText()); 
    										 System.out.println(eU.getName()+"="+eU.getText());
    									}
    								}    
    							}
    						}
//    						//------------------------------------
                            
                            
                            
                            map.put(eBE.getName(), eBE.getText()); 
                            System.out.println(eBE.getName()+"="+eBE.getText());
                        }   
                    }   
                }  
            }   
        }catch(Exception e){   
            e.printStackTrace();  
        }finally{  
            if(f!=null){  
                f.delete();  
            }  
        }  
        return map;  
    }  

	/**
	 * ģ���������XML
	 * @return
	 */
	public static String getXml(String mb,String actoinid,String SPID, String SPServiceID, String FeatureStr){
		return  
		"<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
				"<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP-ENV:Header>" +
				"<TransactionID xmlns=\"http://www.monternet.com/dsmp/schemas/\">"+mb+"</TransactionID>" +
				"</SOAP-ENV:Header><SOAP-ENV:Body>" +
				"<SyncOrderRelationReq xmlns=\"http://www.monternet.com/dsmp/schemas/\">" +
				"<Version>1.5.0</Version>" +
				"<MsgType>SyncOrderRelationReq</MsgType>" +
				"<Send_Address>" +
				"<DeviceType>0</DeviceType>" +
				"<DeviceID>0001</DeviceID>" +
				"</Send_Address>" +
				"<Dest_Address>" +
				"<DeviceType>400</DeviceType>" +
				"<DeviceID>0</DeviceID>" +
				"</Dest_Address>" +
				"<FeeUser_ID>" +
				"<UserIDType>1</UserIDType>" +
				"<MSISDN>"+mb+"</MSISDN>" +
				"<PseudoCode/>" +
				"</FeeUser_ID>" +
				"<DestUser_ID>" +
				"<UserIDType>1</UserIDType>" +
				"<MSISDN>"+mb+"</MSISDN>" +
				"<PseudoCode/>" +
				"</DestUser_ID>" +
				"<LinkID/>" +
				"<ActionID>"+actoinid+"</ActionID>" +
				"<ActionReasonID>1</ActionReasonID>" +
				"<SPID>"+SPID+"</SPID>" +
				"<SPServiceID>"+SPServiceID+"</SPServiceID>" +
				"<AccessMode>3</AccessMode>" +
				"<FeatureStr>"+FeatureStr+"</FeatureStr>" +
				"</SyncOrderRelationReq>" +
				"</SOAP-ENV:Body></SOAP-ENV:Envelope>" ;
		
	}
	/**
	 * ��ӦXML
	 * @param TransactionID ��Ϣ���
	 * @param Version �汾��
	 * @param hRet 0 Ϊ�ɹ���1Ϊʧ��
	 * @return
	 */
	public static String getResultXml(String TransactionID,String Version,String hRet){
		return "<SOAP-ENV:Envelope "+
		"xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" "+
		"xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" "+
		"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "+
		"xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:dsmp=\"http://www.monternet.com/dsmp/schemas/\">"+
		"<SOAP-ENV:Header>"+
		"<dsmp:TransactionID xmlns:dsmp=\"http://www.monternet.com/dsmp/schemas/\">"+
		TransactionID+
		"</dsmp:TransactionID>"+
		"</SOAP-ENV:Header>"+
		"<SOAP-ENV:Body>"+
		"<dsmp:SyncOrderRelationResp xmlns:dsmp=\"http://www.monternet.com/dsmp/schemas/\">"+
		"<MsgType>SyncOrderRelationResp</MsgType>"+
		"<Version>"+Version+"</Version>"+
		"<hRet>"+hRet+"</hRet>"+
		"</dsmp:SyncOrderRelationResp>"+
		"</SOAP-ENV:Body>"+
		"</SOAP-ENV:Envelope>";
	}

}
