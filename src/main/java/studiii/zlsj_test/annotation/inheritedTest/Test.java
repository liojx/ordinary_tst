package studiii.zlsj_test.annotation.inheritedTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Author: liaosijun
 * Description:
 * DateTime: 2018年9月17日 下午3:35:46
 * Modified By:
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Class<ChildClass> cc = ChildClass.class;
		Class<ParentClass> pc = ParentClass.class;
		System.out.println(pc.isAnnotationPresent(InheAnnoTest.class));
		System.out.println(cc.isAnnotationPresent(InheAnnoTest.class));
		Annotation[]  annos = cc.getAnnotations();
		for(Annotation anno : annos) {
			System.out.println(anno.annotationType().getName());
		}
		Class<ChildInterface> ci = ChildInterface.class;
		System.out.println(ci.isAnnotationPresent(InheAnnoTest.class));
	}

}
