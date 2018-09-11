package studiii.zlsj_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import studiii.zlsj_test.pojo.Digo;
import studiii.zlsj_test.service.HitService;

@RestController
@RequestMapping(value = "/T23")
public class T23Control {
	@Autowired
	HitService hitService;
	
	@Autowired
	Digo digo;
	
	@RequestMapping("/T24")
	@ResponseBody
	public String T24() {
//		System.out.println(hitService);
		
		return hitService.hit(digo.getName());
	}
}
