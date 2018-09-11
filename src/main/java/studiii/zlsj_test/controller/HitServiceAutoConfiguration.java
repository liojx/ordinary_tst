package studiii.zlsj_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import studiii.zlsj_test.pojo.Digo;
import studiii.zlsj_test.service.HitService;

@Configuration
@EnableConfigurationProperties(Digo.class)
@ConditionalOnClass(HitService.class)
public class HitServiceAutoConfiguration {

	@Autowired
	private Digo digo;
	
	@Bean
	@ConditionalOnMissingBean(HitService.class)
	public HitService getHitService() {
		HitService hitService = new HitService();
//		hitService.hit(digo.getName());
		return hitService;
	}
	
}
