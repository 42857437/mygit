package msg;

public class MsgBeen {
	private String phone;       //11λ�ֻ�����(�������ƶ�����ͨ)
	private String spnumber;    //SPҵ�����
	private String comnumber;   //ҵ��ش�ȷ����  һ���Ӧ���͵��ط�������
	private String msg;         //MT��������(����������)
	private String linkid;      //MO�����MT�·�ʱ�����ò���
	private String ip;          //MO������ʱIP��ַ
	private String time;        //MO�������ύ����ʱ��
	private String sendtimes;   //���·���MO ���Ŵ���
	private String report;      //����״̬
	private String restatus;    //�۷ѳɹ����״̬����
	private String restatustime;//����״̬ʱ��
	private String webadd;      //��SP�·�MT��ַ
	private String sendurl;     //�������ύ��ַ
	private String sendreport;  //�������ӽ��ӿڱ���
	private String sendip;      //���ܺ������ύip
	private String reporturl;   //������MT״̬�����ַ
	private String upmsg;       //�û�MO��������
	private String restatusurlreport; //���͸�������״̬�����ַ״̬
	private String restatusflag;      //״̬���±��� �Ѹ��¹�����Ϊ"1" 
	private String province;          //ʡ��
	private String city;              //����
	private String msgid;             //msgid ��Ӧ���е�msgid
	private String gwid;              //����ID
	private String productid;         //ҵ�����
	private String servicetype;       //ҵ�����ͣ����ֶ������ƶ�ҵ��ļƷѴ��룬�����������޸ģ�ֱ�ӵ���MO�ж�Ӧ�ֶ�
	private String downmsgid;         //����msgid,��Ӧ״̬����ɹ�msgid
	/**
	 * @return city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city Ҫ���õ� city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return comnumber
	 */
	public String getComnumber() {
		return comnumber;
	}
	/**
	 * @param comnumber Ҫ���õ� comnumber
	 */
	public void setComnumber(String comnumber) {
		this.comnumber = comnumber;
	}
	/**
	 * @return downmsgid
	 */
	public String getDownmsgid() {
		return downmsgid;
	}
	/**
	 * @param downmsgid Ҫ���õ� downmsgid
	 */
	public void setDownmsgid(String downmsgid) {
		this.downmsgid = downmsgid;
	}
	/**
	 * @return gwid
	 */
	public String getGwid() {
		return gwid;
	}
	/**
	 * @param gwid Ҫ���õ� gwid
	 */
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	/**
	 * @return ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * @param ip Ҫ���õ� ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * @return linkid
	 */
	public String getLinkid() {
		return linkid;
	}
	/**
	 * @param linkid Ҫ���õ� linkid
	 */
	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}
	/**
	 * @return msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg Ҫ���õ� msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/**
	 * @return msgid
	 */
	public String getMsgid() {
		return msgid;
	}
	/**
	 * @param msgid Ҫ���õ� msgid
	 */
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	/**
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone Ҫ���õ� phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return productid
	 */
	public String getProductid() {
		return productid;
	}
	/**
	 * @param productid Ҫ���õ� productid
	 */
	public void setProductid(String productid) {
		this.productid = productid;
	}
	/**
	 * @return province
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * @param province Ҫ���õ� province
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * @return report
	 */
	public String getReport() {
		return report;
	}
	/**
	 * @param report Ҫ���õ� report
	 */
	public void setReport(String report) {
		this.report = report;
	}
	/**
	 * @return reporturl
	 */
	public String getReporturl() {
		return reporturl;
	}
	/**
	 * @param reporturl Ҫ���õ� reporturl
	 */
	public void setReporturl(String reporturl) {
		this.reporturl = reporturl;
	}
	/**
	 * @return restatus
	 */
	public String getRestatus() {
		return restatus;
	}
	/**
	 * @param restatus Ҫ���õ� restatus
	 */
	public void setRestatus(String restatus) {
		this.restatus = restatus;
	}
	/**
	 * @return restatusflag
	 */
	public String getRestatusflag() {
		return restatusflag;
	}
	/**
	 * @param restatusflag Ҫ���õ� restatusflag
	 */
	public void setRestatusflag(String restatusflag) {
		this.restatusflag = restatusflag;
	}
	/**
	 * @return restatustime
	 */
	public String getRestatustime() {
		return restatustime;
	}
	/**
	 * @param restatustime Ҫ���õ� restatustime
	 */
	public void setRestatustime(String restatustime) {
		this.restatustime = restatustime;
	}
	/**
	 * @return restatusurlreport
	 */
	public String getRestatusurlreport() {
		return restatusurlreport;
	}
	/**
	 * @param restatusurlreport Ҫ���õ� restatusurlreport
	 */
	public void setRestatusurlreport(String restatusurlreport) {
		this.restatusurlreport = restatusurlreport;
	}
	/**
	 * @return sendip
	 */
	public String getSendip() {
		return sendip;
	}
	/**
	 * @param sendip Ҫ���õ� sendip
	 */
	public void setSendip(String sendip) {
		this.sendip = sendip;
	}
	/**
	 * @return sendreport
	 */
	public String getSendreport() {
		return sendreport;
	}
	/**
	 * @param sendreport Ҫ���õ� sendreport
	 */
	public void setSendreport(String sendreport) {
		this.sendreport = sendreport;
	}
	/**
	 * @return sendtimes
	 */
	public String getSendtimes() {
		return sendtimes;
	}
	/**
	 * @param sendtimes Ҫ���õ� sendtimes
	 */
	public void setSendtimes(String sendtimes) {
		this.sendtimes = sendtimes;
	}
	/**
	 * @return sendurl
	 */
	public String getSendurl() {
		return sendurl;
	}
	/**
	 * @param sendurl Ҫ���õ� sendurl
	 */
	public void setSendurl(String sendurl) {
		this.sendurl = sendurl;
	}
	/**
	 * @return servicetype
	 */
	public String getServicetype() {
		return servicetype;
	}
	/**
	 * @param servicetype Ҫ���õ� servicetype
	 */
	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}
	/**
	 * @return spnumber
	 */
	public String getSpnumber() {
		return spnumber;
	}
	/**
	 * @param spnumber Ҫ���õ� spnumber
	 */
	public void setSpnumber(String spnumber) {
		this.spnumber = spnumber;
	}
	/**
	 * @return time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time Ҫ���õ� time
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * @return upmsg
	 */
	public String getUpmsg() {
		return upmsg;
	}
	/**
	 * @param upmsg Ҫ���õ� upmsg
	 */
	public void setUpmsg(String upmsg) {
		this.upmsg = upmsg;
	}
	/**
	 * @return webadd
	 */
	public String getWebadd() {
		return webadd;
	}
	/**
	 * @param webadd Ҫ���õ� webadd
	 */
	public void setWebadd(String webadd) {
		this.webadd = webadd;
	}
	 

}