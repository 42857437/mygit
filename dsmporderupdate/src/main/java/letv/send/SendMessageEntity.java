package letv.send;

public class SendMessageEntity
{
	private int smsID;
	
	private String[] addresses;
	
	private String addresse;
	
	private String senderAddress;
	
	private String charging;
	
	private String ProductId;
	
	// ????
	private String feecode;
	
	private String linkId;
	
	private String subject;
	
	// int priority=1;
	private String SpId;
	
	private String SpPassword;
	
	private String message;
	
	private String smsURL;
	
	private String SAN;
	
	
	public SendMessageEntity()
	{
		// TODO Auto-generated constructor stub
	}
	
	public String[] getAddresses()
	{
		return addresses;
	}
	
	public void setAddresses(String[] addresses)
	{
		this.addresses = addresses;
	}
	
	public String getCharging()
	{
		return charging;
	}
	
	public void setCharging(String charging)
	{
		this.charging = charging;
	}
	
	public String getFeecode()
	{
		return feecode;
	}
	
	public void setFeecode(String feecode)
	{
		this.feecode = feecode;
	}
	
	public String getLinkId()
	{
		return linkId;
	}
	
	public void setLinkId(String linkId)
	{
		this.linkId = linkId;
	}
	
	public String getProductId()
	{
		return ProductId;
	}
	
	public void setProductId(String productId)
	{
		ProductId = productId;
	}
	
	
	public String getMessage()
	{
		return message;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	public String getSmsURL()
	{
		return smsURL;
	}
	
	public void setSmsURL(String smsURL)
	{
		this.smsURL = smsURL;
	}
	
	public String getSAN()
	{
		return SAN;
	}
	
	public void setSAN(String san)
	{
		SAN = san;
	}
	
	public String getSenderAddress()
	{
		return senderAddress;
	}
	
	public void setSenderAddress(String senderAddress)
	{
		this.senderAddress = senderAddress;
	}
	
	public String getSpId()
	{
		return SpId;
	}
	
	public void setSpId(String spId)
	{
		SpId = spId;
	}
	
	public String getSpPassword()
	{
		return SpPassword;
	}
	
	public void setSpPassword(String spPassword)
	{
		SpPassword = spPassword;
	}
	
	public String getSubject()
	{
		return subject;
	}
	
	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	
	public String getAddresse()
	{
		return addresse;
	}
	
	public void setAddresse(String addresse)
	{
		this.addresse = addresse;
	}

	public int getSmsID()
	{
		return smsID;
	}

	public void setSmsID(int smsID)
	{
		this.smsID = smsID;
	}
	
}
