package studiii.zlsj_test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2 // swagger 生效
@SpringBootApplication(scanBasePackages = "studiii")
@MapperScan(value = "studiii.zlsj_test.*") // 扫描mybatis xml文件目录
public class Application  {

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(Application.class);
//	}

	public static void main(String[] args) {
		 SpringApplication.run(Application.class, args);
	}
}
