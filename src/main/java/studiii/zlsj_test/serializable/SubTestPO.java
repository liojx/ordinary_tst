package studiii.zlsj_test.serializable;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Description: TODO
 * @Author: liaosijun
 * @Time: 2019/7/29 14:26
 */
@Data
@ToString
@Accessors(chain = true)
public class SubTestPO {

	private String key;

	private Object value;

}