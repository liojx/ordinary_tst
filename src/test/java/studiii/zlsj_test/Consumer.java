package studiii.zlsj_test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import studiii.zlsj_test.service.DubboTestService;

/**
 * @author liaosijun
 * @since 2018年12月12日 下午6:20:24
 */
public class Consumer {
	public static void main(String[] args) {
		 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"consumer.xml"});
	        context.start();
	        DubboTestService dubboTestService = (DubboTestService)context.getBean("dubboTestService"); // 获取远程服务代理
	        dubboTestService.getSome("asfdsafdsa");// 执行远程方法
//	        System.out.println( hello ); // 显示调用结果
	}
}
