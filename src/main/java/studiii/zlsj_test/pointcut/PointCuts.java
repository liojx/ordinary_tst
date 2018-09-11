package studiii.zlsj_test.pointcut;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {
//	@Pointcut(value="within(studiii.zlsj_test..*)") //匹配该包及其子包下任何方法执行
	@Pointcut("execution(* studiii.zlsj_test.controller.Xgrcontroller.*(..))") //和上面差不多
	public void aopDemo() {
		
	}
}
