package studiii.zlsj_test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: liaosijun
 * Description:
 * DateTime: 2018年9月11日 下午4:35:43
 * Modified By:
 */
@RestController
public class Xgrcontroller {
	
	@RequestMapping("/Xgr")
	public String Xgr() {
		return "xgr";
	}
}
