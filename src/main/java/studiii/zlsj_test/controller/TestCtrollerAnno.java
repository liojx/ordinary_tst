package studiii.zlsj_test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestCtrollerAnno {

		@RequestMapping("/T27")
		String dir() {
			//要想跳转到制定的jsp中去，需要搭配org.springframework.web.servlet.view.InternalResourceViewResolver才能够跳转
			/**
			 * 配置内容如下;
			 *     <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
			 *       <property name="viewClass"  value="org.springframework.web.servlet.view.JstlView" />
			 *       <property name="prefix" value="/WEB-INF/pages/" />
		     *       <property name="suffix" value=".jsp" />
			 *  	</bean>
			 */
			//由于目前项目不推荐用xml配置都用java配置，故不去实现它
			return "controllerTest.jsp";
		}
}
