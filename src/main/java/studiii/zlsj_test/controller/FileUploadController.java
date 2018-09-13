package studiii.zlsj_test.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ch.qos.logback.core.util.FileUtil;

/**
 * Author: liaosijun
 * Description:
 * DateTime: 2018年9月13日 下午7:54:46
 * Modified By:
 */
@RestController
public class FileUploadController {
	
	@RequestMapping("/upload")
	public @ResponseBody String upl(@RequestBody MultipartFile file) {
		return "OK";
	}
}
