package studiii.zlsj_test.transaction.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import studiii.zlsj_test.transaction.bean.Obj;
import studiii.zlsj_test.transaction.bean.User;
import studiii.zlsj_test.transaction.mapper.UserMapper;

/**
 * @Description: TODO
 * @Author: liaosijun
 * @Time: 2019/5/9 15:31
 */
@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private ObjectService objectService;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Integer insert(User user) throws Exception {
		Integer integer = null;
		try {
			integer = userMapper.insert(user);
			if(integer > 0){
				Obj obj = new Obj();
				obj.setName("computer");
				obj.setId(user.getId());
				obj.setuId(user.getId());
				objectService.insertObj(obj);
			}
		}catch (Exception e){
			e.printStackTrace();
		}

		return integer;
	}


}