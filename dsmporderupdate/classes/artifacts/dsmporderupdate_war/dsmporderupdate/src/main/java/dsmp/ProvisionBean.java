package dsmp;


import java.io.Serializable;


/**
 * ����ͬ����Ϣbean
 * @author admin_Hzw
 *
 */

public class ProvisionBean  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8504222411705338959L;
	private Long id;  //����
	private String msisdnu; //�Ʒ��û���ʶ
	private String MSISDN_D;  //ʹ���û���ʶ

	private String TransactionID; //��Ϣ���
	private String MsgType; //��Ϣ����
	private String Version;//�ýӿ���Ϣ�İ汾�ţ��������еĽӿ���Ϣ�İ汾��Ϊ��1.5.0��
	//--------------------
	private String LinkID;  //��ʱ������ϵ������ID
	/*
	 * ����״̬���������룬����ֵ���£�
		1�� ��ͨ����
		2�� ֹͣ����
		3�� �������
		4�� ��ͣ����
		5: ������ã�
		8: ������ȷ�� 
	 */
	private String ActionID; 
	/*
	 * ��������״̬������ԭ��Ĵ��룬����ֵ���£�
		1���û�������Ϊ
		2��ϵͳ������Ϊ
		3���۷�ʧ�ܵ��µķ���ȡ��
		4������
	 */ 
	private String ActionReasonID;
	private String SPID;  //SP����ҵ����
	private String SPServiceID; //SP�и÷���ķ������
	private String FeatureStr; //���񶩹������ڶ���MO�����У�FeatureStr�д��͵���AccessNo+�������ݡ�
	private String platUserId;//ƽ̨���û�id
	private String createtime; //����ʱ��
	private String status;//״̬0Ϊ�����ɹ���1Ϊδ�ɹ�
	private String Pno; //��Ʒ���
	
	
	
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
  