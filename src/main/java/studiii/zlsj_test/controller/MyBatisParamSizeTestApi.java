package studiii.zlsj_test.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import studiii.zlsj_test.controller.dao.GisDevExtPOMapper;
import studiii.zlsj_test.controller.model.SpaceInfTotalPO;
import studiii.zlsj_test.util.io.FileUtil;

import java.util.List;

/**
 * @Description: TODO
 * @Author: liaosijun
 * @Time: 2019/7/10 13:13
 */
@RestController
@RequestMapping(value = "/mybatis-test", method = RequestMethod.POST)
public class MyBatisParamSizeTestApi {

	private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(MyBatisParamSizeTestApi.class);

	@Autowired
	GisDevExtPOMapper gisDevExtPOMapper;

	@ApiOperation(value = "测试Mybatis的参数最大可以传输多少字节")
	@RequestMapping(value = "testMybatisPram")
	public void testMybatisPram() throws Exception {
		try {
			String devStr = getStr();
			List<SpaceInfTotalPO> list = gisDevExtPOMapper.findSpaceInfoByDevIds(devStr);
			Logger.debug("list.size() = " + list.size());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private String getStr() {
		String s = FileUtil.fileToString("d:/temp/sizeCount.txt");
		return s;
	}
}