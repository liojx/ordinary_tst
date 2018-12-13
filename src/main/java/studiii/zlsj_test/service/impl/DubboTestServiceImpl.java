package studiii.zlsj_test.service.impl;

import studiii.zlsj_test.service.DubboTestService;

/**
 * @author liaosijun
 * @since 2018年12月12日 下午3:46:51
 */
public class DubboTestServiceImpl implements DubboTestService {

	/* (non-Javadoc)
	 * @see studiii.zlsj_test.service.DubboTestService#getSome(java.lang.String)
	 */
	@Override
	public void getSome(String a) {
		// TODO Auto-generated method stub
		System.out.println("\t \r ->" + a);
	}

}
