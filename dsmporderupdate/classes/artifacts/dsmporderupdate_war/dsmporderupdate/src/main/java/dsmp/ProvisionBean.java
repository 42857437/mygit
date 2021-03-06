package dsmp;


import java.io.Serializable;


/**
 * 订购同步消息bean
 * @author admin_Hzw
 *
 */

public class ProvisionBean  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8504222411705338959L;
	private Long id;  //主键
	private String msisdnu; //计费用户标识
	private String MSISDN_D;  //使用用户标识

	private String TransactionID; //消息编号
	private String MsgType; //消息类型
	private String Version;//该接口消息的版本号，本次所有的接口消息的版本都为“1.5.0”
	//--------------------
	private String LinkID;  //临时订购关系的事务ID
	/*
	 * 服务状态管理动作代码，具体值如下：
		1： 开通服务；
		2： 停止服务；
		3： 激活服务；
		4： 暂停服务；
		5: 免费试用；
		8: 第三方确认 
	 */
	private String ActionID; 
	/*
	 * 产生服务状态管理动作原因的代码，具体值如下：
		1：用户发起行为
		2：系统发起行为
		3：扣费失败导致的服务取消
		4：其他
	 */ 
	private String ActionReasonID;
	private String SPID;  //SP的企业代码
	private String SPServiceID; //SP中该服务的服务代码
	private String FeatureStr; //服务订购参数在短信MO流程中，FeatureStr中传送的是AccessNo+短信内容。
	private String platUserId;//平台侧用户id
	private String createtime; //创建时间
	private String status;//状态0为订购成功，1为未成功
	private String Pno; //产品编号
	
	
	
	public String getMsisdnu() {
		return msisdnu;
	}
	public void setMsisdnu(String msisdnu) {
		this.msisdnu = msisdnu;
	}
	public String getMSISDN_D() {
		return MSISDN_D;
	}
	public void setMSISDN_D(String mSISDND) {
		MSISDN_D = mSISDND;
	}
	public String getTransactionID() {
		return TransactionID;
	}
	public void setTransactionID(String transactionID) {
		TransactionID = transactionID;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getVersion() {
		return Version;
	}
	public void setVersion(String version) {
		Version = version;
	}
	public String getLinkID() {
		return LinkID;
	}
	public void setLinkID(String linkID) {
		LinkID = linkID;
	}
	public String getActionID() {
		return ActionID;
	}
	public void setActionID(String actionID) {
		ActionID = actionID;
	}
	public String getActionReasonID() {
		return ActionReasonID;
	}
	public void setActionReasonID(String actionReasonID) {
		ActionReasonID = actionReasonID;
	}
	public String getSPID() {
		return SPID;
	}
	public void setSPID(String sPID) {
		SPID = sPID;
	}
	public String getSPServiceID() {
		return SPServiceID;
	}
	public void setSPServiceID(String sPServiceID) {
		SPServiceID = sPServiceID;
	}
	public String getFeatureStr() {
		return FeatureStr;
	}
	public void setFeatureStr(String featureStr) {
		FeatureStr = featureStr;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPlatUserId() {
		return platUserId;
	}
	public void setPlatUserId(String platUserId) {
		this.platUserId = platUserId;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPno() {
		return Pno;
	}
	public void setPno(String pno) {
		Pno = pno;
	}
	
}
  