package msg;

public class MsgBeen {
	private String phone;       //11位手机号码(可以是移动和联通)
	private String spnumber;    //SP业务号码
	private String comnumber;   //业务回传确认码  一般对应发送的特服长号码
	private String msg;         //MT短信内容(合作方定义)
	private String linkid;      //MO引起的MT下发时附带该参数
	private String ip;          //MO服务器时IP地址
	private String time;        //MO服务器提交数据时间
	private String sendtimes;   //当月发送MO 短信次数
	private String report;      //报告状态
	private String restatus;    //扣费成功与否状态报告
	private String restatustime;//返回状态时间
	private String webadd;      //给SP下发MT地址
	private String sendurl;     //合作方提交地址
	private String sendreport;  //合作方接交接口报告
	private String sendip;      //接受合作方提交ip
	private String reporturl;   //合作方MT状态报告地址
	private String upmsg;       //用户MO短信内容
	private String restatusurlreport; //发送给合作方状态报告地址状态
	private String restatusflag;      //状态更新报告 已更新过的设为"1" 
	private String province;          //省份
	private String city;              //城市
	private String msgid;             //msgid 对应上行的msgid
	private String gwid;              //网关ID
	private String productid;         //业务编码
	private String servicetype;       //业务类型，该字段用做移动业务的计费代码，第三方不用修改，直接调用MO中对应字段
	private String downmsgid;         //下行msgid,对应状态报告成功msgid
	/**
	 * @return city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city 要设置的 city
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
	 * @param comnumber 要设置的 comnumber
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
	 * @param downmsgid 要设置的 downmsgid
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
	 * @param gwid 要设置的 gwid
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
	 * @param ip 要设置的 ip
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
	 * @param linkid 要设置的 linkid
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
	 * @param msg 要设置的 msg
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
	 * @param msgid 要设置的 msgid
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
	 * @param phone 要设置的 phone
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
	 * @param productid 要设置的 productid
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
	 * @param province 要设置的 province
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
	 * @param report 要设置的 report
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
	 * @param reporturl 要设置的 reporturl
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
	 * @param restatus 要设置的 restatus
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
	 * @param restatusflag 要设置的 restatusflag
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
	 * @param restatustime 要设置的 restatustime
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
	 * @param restatusurlreport 要设置的 restatusurlreport
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
	 * @param sendip 要设置的 sendip
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
	 * @param sendreport 要设置的 sendreport
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
	 * @param sendtimes 要设置的 sendtimes
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
	 * @param sendurl 要设置的 sendurl
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
	 * @param servicetype 要设置的 servicetype
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
	 * @param spnumber 要设置的 spnumber
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
	 * @param time 要设置的 time
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
	 * @param upmsg 要设置的 upmsg
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
	 * @param webadd 要设置的 webadd
	 */
	public void setWebadd(String webadd) {
		this.webadd = webadd;
	}
	 

}