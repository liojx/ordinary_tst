package studiii.zlsj_test.annotation.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: liaosijun
 * Description:
 * DateTime: 2018年9月20日 下午5:03:58
 * Modified By:
 */
@RestController
@RequestMapping("/T33")
public class TestController {
	
	public LDY ldy = null;
	
	@Autowired
	ConfigurationTest conf ;
	
	
	@SuppressWarnings("resource")
	@RequestMapping("/bb")
	public void tto() {
		// 浏览器请求 http://localhost:8088/T33/bb
		System.out.println("当前Rquest下的实例地址：" + conf.getSixteenLDY());  //因为作用域为singleton ，所以每次刷新请求都是同一个实例
		
//		System.out.println(new AnnotationConfigApplicationContext().getBean(conf.getSixteenLDY().getClass()));
		System.out.println("获取另外一个：" + conf.oldlLdy()); //作用于为prototype，每次刷新请求都在变
	}
	
}
