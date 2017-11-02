package letv.send;

import java.util.Vector;


public class thirdParter {
	    @SuppressWarnings("unchecked")
		public static Vector<Msg> vec = new Vector();
	    public thirdParter()
	    {
	    }

	    public static void Add2Vector(String msgtype,
	    		                      String TransactionID, //????ID
	    		                      String Version,
	                                  String Send_Address,
	                                  String Dest_Address,
	                                  String Original_Address,
	                                  String FeeUser_ID,
	                                  String DestUser_ID,
	                                  String LinkID,
	                                  Integer ActionID,
	                                  Integer ActionReasonID,
	                                  String  SPID,
	                                  String  SPServiceID,
	                                  Integer AccessMode,
	                                  String FeatureStr,
	                                  String url
	                                  )
	    {
	        Msg m = new Msg();
	        m.msgtype = msgtype;
	        m.TransactionID = TransactionID;
	        m.Version = Version;
	        m.Send_Address = Send_Address;
	        m.Dest_Address = Dest_Address;
	        m.Original_Address  = Original_Address;
	        m.FeeUser_ID = FeeUser_ID;
	        m.DestUser_ID = DestUser_ID;
	        m.LinkID = LinkID;
	        m.ActionID = ActionID;
	        m.ActionReasonID = ActionReasonID;
	        m.SPID = SPID;
	        m.SPServiceID = SPServiceID;
	        m.AccessMode = AccessMode;
	        m.FeatureStr = FeatureStr;
	        m.url = url;
	      
	        vec.add(m);
	        m = null;
	    }
	}
