package msg;

import javax.xml.soap.*;
import java.io.ByteArrayInputStream;
import java.util.Iterator;

public class SoapUtil {
    
    /**
     * 解析soapXML
     * @param soapXML
     * @return
     */
    public static WebserviceResultBean parseSoapMessage(String soapXML) {
        WebserviceResultBean resultBean = new WebserviceResultBean();
        try {
            SOAPMessage msg = formatSoapString(soapXML);
            SOAPBody body = msg.getSOAPBody();
            Iterator<SOAPElement> iterator = body.getChildElements();
            parse(iterator, resultBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultBean;
    }

    public static void main(String[] args) {
        System.out.println("开始解析soap...");

        //String deptXML = "<SOAP:Envelope xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP:Header/><SOAP:Body><ns:OP_SDMS_Consume_Material_SynResponse xmlns:ns=\"http://toSDMS.material.service.ffcs.com\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><ns:return><ns:BASEINFO><MSGID>?</MSGID><PMSGID>?</PMSGID><SENDTIME>20140212094609</SENDTIME><S_PROVINCE>?</S_PROVINCE><S_SYSTEM>?</S_SYSTEM><SERVICENAME>?</SERVICENAME><T_PROVINCE>?</T_PROVINCE><T_SYSTEM>?</T_SYSTEM><RETRY>?</RETRY></ns:BASEINFO><ns:MESSAGE><RESULT>E</RESULT><REMARK/><XMLDATA><![CDATA[<response><RESULT>E</RESULT><MESSAGE>平台系统处理时发生异常!保存接口接收数据出错!</MESSAGE></response>]]></XMLDATA></ns:MESSAGE></ns:return></ns:OP_SDMS_Consume_Material_SynResponse></SOAP:Body></SOAP:Envelope>";
        String deptXML = "<?xml version=\"1.0\" encoding=\"utf-8\" ?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Header><ns1:RequestSOAPHeader xmlns:ns1=\"http://www.huawei.com.cn/schema/common/v2_1\"><ns1:spRevpassword>1FAEDF7F2A10D44F435321D966E723A3</ns1:spRevpassword><ns1:spId>711111</ns1:spId><ns1:serviceId>760000116700</ns1:serviceId><ns1:timeStamp>20160603063259</ns1:timeStamp><ns1:traceUniqueID>504010102631606031432411786005</ns1:traceUniqueID><ns1:webID>1914583243</ns1:webID></ns1:RequestSOAPHeader></soapenv:Header><soapenv:Body><ns2:syncOrderRelation xmlns:ns2=\"http://www.csapi.org/schema/parlayx/data/sync/v1_0/local\"><ns2:userID><ns3:ID xmlns:ns3=\"http://schema.ib.sdp.huawei.com\">1708036910</ns3:ID><ns4:type xmlns:ns4=\"http://schema.ib.sdp.huawei.com\">1</ns4:type></ns2:userID><ns2:productID>760000116700</ns2:productID><ns2:serviceID>760000116700</ns2:serviceID><ns2:serviceList>006113338000</ns2:serviceList><ns2:updateType>1</ns2:updateType><ns2:updateTime>20160603063243</ns2:updateTime><ns2:updateDesc>Addition</ns2:updateDesc><ns2:effectiveTime>20160603063243</ns2:effectiveTime><ns2:expiryTime>20370101000000</ns2:expiryTime><ns2:extensionInfo><ns5:namedParameters xmlns:ns5=\"http://enduserservices.ib.sdp.huawei.com\"><ns6:key xmlns:ns6=\"http://schema.ib.sdp.huawei.com\">productOrderKey</ns6:key><ns7:value xmlns:ns7=\"http://schema.ib.sdp.huawei.com\">999000000638274455</ns7:value></ns5:namedParameters><ns8:namedParameters xmlns:ns8=\"http://enduserservices.ib.sdp.huawei.com\"><ns9:key xmlns:ns9=\"http://schema.ib.sdp.huawei.com\">outterId</ns9:key><ns10:value xmlns:ns10=\"http://schema.ib.sdp.huawei.com\">1914583243</ns10:value></ns8:namedParameters></ns2:extensionInfo></ns2:syncOrderRelation></soapenv:Body></soapenv:Envelope>";


        WebserviceResultBean ret = parseSoapMessage(deptXML);
        try {
            SOAPMessage msg = formatSoapString(deptXML);
            SOAPBody body = msg.getSOAPBody();
            Iterator<SOAPElement> iterator = body.getChildElements();
            PrintBody(iterator, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("解析soap结束...");
    }

    /**
     * 把soap字符串格式化为SOAPMessage
     * 
     * @param soapString
     * @return
     * @see [类、类#方法、类#成员]
     */
    private static SOAPMessage formatSoapString(String soapString) {
        MessageFactory msgFactory;
        try {
            msgFactory = MessageFactory.newInstance();
            SOAPMessage reqMsg = msgFactory.createMessage(new MimeHeaders(),
                    new ByteArrayInputStream(soapString.getBytes("UTF-8")));
            reqMsg.saveChanges();
            return reqMsg;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析soap xml
     * @param iterator
     * @param resultBean
     */
    private static void parse(Iterator<SOAPElement> iterator, WebserviceResultBean resultBean) {
        while (iterator.hasNext()) {
            SOAPElement element = iterator.next();
//            System.out.println("Local Name:" + element.getLocalName());
//            System.out.println("Node Name:" + element.getNodeName());
//            System.out.println("Tag Name:" + element.getTagName());
//            System.out.println("Value:" + element.getValue());
            if ("ns:BASEINFO".equals(element.getNodeName())) {
                continue;
            } else if ("ns:MESSAGE".equals(element.getNodeName())) {
                Iterator<SOAPElement> it = element.getChildElements();
                SOAPElement el = null;
                while (it.hasNext()) {
                    el = it.next();
                    if ("RESULT".equals(el.getLocalName())) {
                        resultBean.setResult(el.getValue());
                        System.out.println("#### " + el.getLocalName() + "  ====  " + el.getValue());
                    } else if ("REMARK".equals(el.getLocalName())) {
                        resultBean.setRemark(null != el.getValue() ? el.getValue() : "");
                        System.out.println("#### " + el.getLocalName() + "  ====  " + el.getValue());
                    } else if ("XMLDATA".equals(el.getLocalName())) {
                        resultBean.setXmlData(el.getValue());
                        System.out.println("#### " + el.getLocalName() + "  ====  " + el.getValue());
                    }
                }
            } else if (null == element.getValue()
                    && element.getChildElements().hasNext()) {
                parse(element.getChildElements(), resultBean);
            }
        }
    }
    
    private static void PrintBody(Iterator<SOAPElement> iterator, String side) {
        while (iterator.hasNext()) {
            SOAPElement element = (SOAPElement) iterator.next();
            System.out.println("Local Name:" + element.getLocalName());
            System.out.println("Node Name:" + element.getNodeName());
            System.out.println("Tag Name:" + element.getTagName());
            System.out.println("Value:" + element.getValue());
            if (null == element.getValue()
                    && element.getChildElements().hasNext()) {
                PrintBody(element.getChildElements(), side + "-----");
            }
        }
    }
}