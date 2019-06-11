package studiii.zlsj_test.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @Author: liaosijun
 * @Time: 2019/6/10 20:14
 */
@RestController
@RequestMapping(value = "/gis")
public class GISTESTController {
	@ResponseBody
	@PostMapping("addPram")
	private void addParm(){
		System.out.println("add.param");
	}
}