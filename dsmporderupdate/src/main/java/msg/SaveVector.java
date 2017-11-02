package msg;

import java.util.Vector;

public class SaveVector {
	public static Vector vec = new Vector();
	public static Vector vec2 = new Vector();
	public static Vector vec3 = new Vector();

	public SaveVector() {

	}

	public static void Add2Vector(
		String phone,
		String restatus,
		String spnumber,
		String downmsgid,
		String ip) {
		MsgBeen m = new MsgBeen();
		m.setPhone(phone);
		m.setRestatus(restatus);
		m.setSpnumber(spnumber);
		m.setDownmsgid(downmsgid);
		m.setIp(ip);
		vec.add(m);
		m = null;
	}

	public static void AddMOVector(
		String phone,
		String restatus,
		String spnumber,
		String linkid,
		String upmsg,
		String msgid,
		String gwID,
		String FProductID,
		String serviceType,
		String RemoteAddr) {
		MsgBeen m2 = new MsgBeen();
		m2.setPhone(phone);
		m2.setRestatus(restatus);
		m2.setSpnumber(spnumber);
		m2.setLinkid(linkid);
		m2.setMsg(upmsg);
		m2.setMsgid(msgid);
		m2.setGwid(gwID);
		m2.setProductid(FProductID);
		m2.setServicetype(serviceType);
		m2.setIp(RemoteAddr);
		vec2.add(m2);
		m2 = null;

	}
	
	public static void AddMTSendVector(		
			String reporturl,
			String linkid,
			String downmsgid) {
			MsgBeen m3 = new MsgBeen();
			m3.setReporturl(reporturl);				
			m3.setLinkid(linkid);	
			m3.setDownmsgid(downmsgid);
			vec3.add(m3);
			m3 = null;

		}
	public static void main(String arg[]) {
		try {
			Add2Vector("13520250363", "OK", "", "", "");
			Add2Vector("13810366355", "0", "", "", "");
			Add2Vector("13990366355", "ok", "", "", "");
			Add2Vector("13990366355", "ok", "", "", "");
			Vector vector = new Vector();
			synchronized (SaveVector.vec) {
				if (SaveVector.vec.size() > 0) {
					vector = new Vector(SaveVector.vec.size());
					for (int j = 0; j < SaveVector.vec.size(); j++) {
						vector.add(SaveVector.vec.get(j));
					}
					SaveVector.vec.clear();
					System.out.println("clear datas:" + vector.size());
				}

				for (int k = 0; k < vector.size(); k++) {
					MsgBeen mg = (MsgBeen) vector.get(k);
					System.out.print(mg.getPhone());
					System.out.print(mg.getRestatus());
					Thread.sleep(1500);

					mg = null;
					System.out.println("insert data   finish");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}