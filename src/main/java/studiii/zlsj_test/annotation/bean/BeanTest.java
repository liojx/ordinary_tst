package studiii.zlsj_test.annotation.bean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;

import studiii.zlsj_test.Application;

/**
 * Author: liaosijun
 * Description:
 * DateTime: 2018年9月18日 下午4:18:25
 * Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@RequestMapping("T33")
public class BeanTest {
	
	LDY ld = null;
	
	
	@Autowired
	ConfigurationTest conf ;
	
	@Test
	@RequestMapping("/aa")
	public void test() {
		System.out.println(1);
		ld = conf.oldlLdy();
		System.out.println(ld.toString());
	}
}
