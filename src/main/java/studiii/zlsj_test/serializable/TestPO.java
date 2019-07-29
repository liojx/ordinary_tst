package studiii.zlsj_test.serializable;

import lombok.Data;
import lombok.ToString;

/**
 * @Description: TODO
 * @Author: liaosijun
 * @Time: 2019/7/29 13:55
 */
@Data
@ToString
public class TestPO {

	private String id;

	private String name;

	private String val;

	private Short delFlag;

	private SubTestPO subTestPO;
}