package studiii.zlsj_test.transaction.bean;

import lombok.Data;

/**
 * @Description: TODO
 * @Author: liaosijun
 * @Time: 2019/5/9 15:29
 */
@Data
public class Obj {
	private Integer id;
	private String name;
	private Integer uId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}
}