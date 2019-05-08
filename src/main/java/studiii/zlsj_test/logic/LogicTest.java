package studiii.zlsj_test.logic;

import static studiii.zlsj_test.annotation.DocumentDemo.b;

/**
 * @Description: TODO
 * @Author: liaosijun
 * @Time: 2019/5/8 10:16
 */
public class LogicTest {
	static void l1(){
		String startTime = "1";
		String endTime = "";
		if (org.springframework.util.StringUtils.isEmpty(startTime) && org.springframework.util.StringUtils.isEmpty(endTime)){
			System.out.println("startTime && endTime都为空串或者null");
		}

		if (org.springframework.util.StringUtils.isEmpty(startTime) || org.springframework.util.StringUtils.isEmpty(endTime)){
			System.out.println("startTime || endTime满足一个以上为空串或者null");
		}
	}
	public static void main(String[] args) {
		l1();
	}
}