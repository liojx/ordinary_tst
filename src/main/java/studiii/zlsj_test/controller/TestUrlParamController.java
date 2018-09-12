package studiii.zlsj_test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: liaosijun
 * Description:
 * DateTime: 2018年9月11日 下午7:11:29
 * Modified By:
 */
@RestController
@RequestMapping("/TestUrlParam")
public class TestUrlParamController {

	@RequestMapping("T33")
	@ResponseBody
	public String gotoTestPra(String param1, String param2, String param3) {
		System.out.println("param1 ==== "+ param1);
		System.out.println("param2 ==== "+ param2);
		System.out.println("param3 ==== "+ param3);
		return param1 + param2 + param3;
	}
}
