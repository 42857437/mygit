package com.bill99.mgw.core;

import com.bill99.mgw.entity.MerchantInfo;

import java.io.InputStream;

/**
 * @project mgwCore
 * @description:���ݷ��ͽӿ�
 * @author cen
 * @create_time:Jun 22, 2009
 * @modify_time:Jun 22, 2009
 */
public interface PostTr1Processor {
	/**
	 * @param req ���Ͳ�������
	 * @return ���񷵻�
	 * @throws Exception
	 */
	public InputStream post(MerchantInfo req) throws Exception;
}
