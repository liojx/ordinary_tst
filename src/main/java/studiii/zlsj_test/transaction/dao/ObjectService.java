package studiii.zlsj_test.transaction.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import studiii.zlsj_test.transaction.bean.Obj;
import studiii.zlsj_test.transaction.mapper.ObjMapper;

/**
 * @Description: TODO
 * @Author: liaosijun
 * @Time: 2019/5/10 9:12
 */
@Service
public class ObjectService {

	@Autowired
	private ObjMapper objMapper;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Integer insertObj(Obj obj) throws Exception{
		return objMapper.insert(obj);
	}
}