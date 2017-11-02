package com.bill99.mgw.core;

import com.bill99.mgw.entity.MerchantInfo;

import java.io.InputStream;

/**
 * @project mgwCore
 * @description:数据发送接口
 * @author cen
 * @create_time:Jun 22, 2009
 * @modify_time:Jun 22, 2009
 */
public interface PostTr1Processor {
	/**
	 * @param req 发送参数对象
	 * @return 服务返回
	 * @throws Exception
	 */
	public InputStream post(MerchantInfo req) throws Exception;
}
