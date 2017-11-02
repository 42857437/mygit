/**
 * 
 */
package com.bill99.mgw.util;

import java.io.InputStream;
import java.util.Properties;


/**
 * <p>Title:mip</p>
 * <p>Description:</p>
 * <p>Copyright:</p>
 * <p>Company: </p>
 * @author panqr
 * @version 
 */
public class PropFile {
	private static Properties props = null;
	
	private PropFile(){
		
	}
	
	public static synchronized Properties getProps(String propFile) throws Exception{
		if (props == null){
			props = new Properties();
			InputStream in = null;
			try{
//				in = new FileInputStream(propFile);
				in=PropFile.class.getResourceAsStream(propFile);
				props.load(in);
				return props;
			}finally{
				if (in != null){
					in.close();
				}
			}
		}else{
			return props;
		}
	}

}
