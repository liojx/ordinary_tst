package studiii.zlsj_test.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studiii.zlsj_test.controller.dao.APOMapper;
import studiii.zlsj_test.controller.model.APO;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @Description: TODO
 * @Author: liaosijun
 * @Time: 2019/7/31 14:00
 */
@Api("Auto DB primary key , after insert, get the primary key")
@RestController
@RequestMapping(value = "/autoGetKey", method = POST)
public class TestGenerateIdApi {

	@Autowired
	private APOMapper apoMapper;

	@ApiOperation(value = "test Mybatis after insert get the primary key")
	@RequestMapping(value = "testId")
	public void testId() throws Exception {
		try {
			APO apo = new APO();
			apo.setVal("nidsdf");
			int aff = apoMapper.insertSelective(apo);
			System.out.println(apo.getId());
			System.out.println("insert ok！");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}