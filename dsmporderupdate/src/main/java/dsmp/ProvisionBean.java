//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dsmp;

import java.io.Serializable;

public class ProvisionBean implements Serializable {
	private static final long serialVersionUID = -8504222411705338959L;
	private Long id;
	private String msisdnu;
	private String MSISDN_D;
	private String TransactionID;
	private String MsgType;
	private String Version;
	private String LinkID;
	private String ActionID;
	private String ActionReasonID;
	private String SPID;
	private String SPServiceID;
	private String FeatureStr;
	private String platUserId;
	private String createtime;
	private String status;
	private String Pno;

	public ProvisionBean() {
	}

	public String getMsisdnu() {
		return this.msisdnu;
	}

	public void setMsisdnu(String msisdnu) {
		this.msisdnu = msisdnu;
	}

	public String getMSISDN_D() {
		return this.MSISDN_D;
	}

	public void setMSISDN_D(String mSISDND) {
		this.MSISDN_D = mSISDND;
	}

	public String getTransactionID() {
		return this.TransactionID;
	}

	public void setTransactionID(String transactionID) {
		this.TransactionID = transactionID;
	}

	public String getMsgType() {
		return this.MsgType;
	}

	public void setMsgType(String msgType) {
		this.MsgType = msgType;
	}

	public String getVersion() {
		return this.Version;
	}

	public void setVersion(String version) {
		this.Version = version;
	}

	public String getLinkID() {
		return this.LinkID;
	}

	public void setLinkID(String linkID) {
		this.LinkID = linkID;
	}

	public String getActionID() {
		return this.ActionID;
	}

	public void setActionID(String actionID) {
		this.ActionID = actionID;
	}

	public String getActionReasonID() {
		return this.ActionReasonID;
	}

	public void setActionReasonID(String actionReasonID) {
		this.ActionReasonID = actionReasonID;
	}

	public String getSPID() {
		return this.SPID;
	}

	public void setSPID(String sPID) {
		this.SPID = sPID;
	}

	public String getSPServiceID() {
		return this.SPServiceID;
	}

	public void setSPServiceID(String sPServiceID) {
		this.SPServiceID = sPServiceID;
	}

	public String getFeatureStr() {
		return this.FeatureStr;
	}

	public void setFeatureStr(String featureStr) {
		this.FeatureStr = featureStr;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlatUserId() {
		return this.platUserId;
	}

	public void setPlatUserId(String platUserId) {
		this.platUserId = platUserId;
	}

	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPno() {
		return this.Pno;
	}

	public void setPno(String pno) {
		this.Pno = pno;
	}
}
