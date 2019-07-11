package studiii.zlsj_test.controller.model;

import lombok.Data;

/**
 * @Description: 空间查询的列表信息
 * @Author: liaosijun
 * @Time: 2019/6/12 11:15
 */
@Data
public class SpaceInfTotalPO {

	/**
	 * 图层名称
	 */
	private String coverageName;

	/**
	 * 数量
	 */
	private Long number;

	/**
	 * id
	 */
	private Long id;
}