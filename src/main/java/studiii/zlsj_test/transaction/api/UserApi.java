package studiii.zlsj_test.transaction.api;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import studiii.zlsj_test.transaction.bean.User;
import studiii.zlsj_test.transaction.dao.UserService;

/**
 * @Description: TODO
 * @Author: liaosijun
 * @Time: 2019/5/8 18:02
 */
@RestController
@RequestMapping(value="/users")
public class UserApi {

	@Autowired
	private UserService userService;

	@ApiOperation(value="创建用户", notes="根据User对象创建用户")
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
	@RequestMapping(value="creatUser", method= RequestMethod.POST)
	public String postUser(@RequestBody User user) {
		try {
			System.out.println("insert user api ...");
			userService.insert(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

}