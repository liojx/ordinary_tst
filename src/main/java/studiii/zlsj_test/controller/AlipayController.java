package studiii.zlsj_test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description: TODO
 * @Author: liaosijun
 * @Time: 2019/5/10 18:56
 */
@RestController
@RequestMapping(value = "/ali")
public class AlipayController {

	@RequestMapping(value = "/test")
	public String test() {
		System.out.println("进入了/ali/test");
		return "";
	}

	@RequestMapping(value = "/index")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("alipay.trade.query");
		return mv;
	}


}