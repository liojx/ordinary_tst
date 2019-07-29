package studiii.zlsj_test.serializable;

import com.google.common.collect.Maps;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * @Description: map转换成bean
 * @Author: liaosijun
 * @Time: 2019/7/29 13:56
 */
public class SerialTest {

	public static void main(String[] args) {
		HashMap map = Maps.newHashMap();
		map.put("id", "111");
		map.put("name","liaodaye");
		map.put("val","xfy");
		map.put("delFlag",0);
		map.put("subTestPO", new SubTestPO().setValue(999999999999999L).setKey("ccx"));

		try {
			TestPO testPO = TestPO.class.newInstance();
			BeanUtils.populate(testPO,map);
			System.out.println(testPO.toString());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}
}