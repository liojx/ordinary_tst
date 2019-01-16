package studiii.zlsj_test.annotation.inheritedTest;

//import studiii.zlsj_test.annotation.target.AnnotationTarget;

/**
 * Author: liaosijun
 * Description:
 * DateTime: 2018年9月17日 下午2:36:39
 * Modified By:
 */
@InheAnnoTest("Just Parent")
public class ParentClass {
	
	private String name ;
	
//	@AnnotationTarget("yes, i am 15")
	String age;
	
	@MethodTestAnno("yeah")
	public void attack() {
		
	}
	@InheAnnoTest("ojbk")
	protected String plus() {
		return "";
	}
}
