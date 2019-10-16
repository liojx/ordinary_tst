package studiii.zlsj_test.pointcut;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {
	//	@Pointcut(value="within(studiii.zlsj_test..*)") //匹配该包及其子包下任何方法执行
	/**** 第一个* 代表返回任何类型  包后面的.表示当前包 包后面第一个*代表当前包的任何类，第二个*代表任何方法，括号里面的两个点代表任何参数*/
	@Pointcut("execution(* studiii.zlsj_test.controller.*.*(..))") //和上面差不多
	public void aopDemo() {
		
	}
}
