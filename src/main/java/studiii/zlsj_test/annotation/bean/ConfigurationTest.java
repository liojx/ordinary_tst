package studiii.zlsj_test.annotation.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Author: liaosijun
 * Description:
 * DateTime: 2018年9月19日 上午10:40:18
 * Modified By:
 */
@Configuration
public class ConfigurationTest {
	
	@Bean("Sixteen")
	@Scope("singleton")
	public LDY getSixteenLDY() {
		LDY ld = new LDY();
		ld.setA("beauty");
		ld.setB("younger");
		ld.setC("happy");
		return ld;
	}
	
	@Bean
	@Scope("prototype")         // prototype据说是request 级别，bean是单例，请求一个生成一个新的bean
	public LDY oldlLdy() {
		LDY ld = new LDY();
		ld.setA("Qie doctor");
		ld.setB("Java");
		ld.setC("liaosjjjjj");
		return ld;
	}
}
