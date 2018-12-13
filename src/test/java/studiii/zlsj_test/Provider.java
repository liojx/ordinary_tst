package studiii.zlsj_test;

import java.io.File;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author liaosijun
 * @since 2018年12月12日 下午5:32:11
 */
public class Provider {

	    public static void main(String[] args) throws Exception {
	    	File f = new File(Provider.class.getClassLoader().getResource("provider.xml").getPath());
	    	System.out.println(f.exists());
	        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"provider.xml"});
	        context.start();
	        System.in.read(); // 按任意键退出
	    }
}
