package studiii.zlsj_test.dispelTheCloudsAndSeeTheSun;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Jiyn(description="Jin .. In ..Tin .. End")
public class Test implements Jiyn{
	@SuppressWarnings(value = { "all" })
	public static void main(String[] args) {
		Test t = new Test();
		Class c = t.getClass();
		//这里要知道，倘若Jiyn没有添加Retention，并且Runtime可见，该对象会为nUll.
		Jiyn ji = (Jiyn) c.getAnnotation(Jiyn.class);// 类Test的class 是由有Jiyn类型的注解
		System.out.println(ji.getClass().getTypeName());
		try {
			Method method = c.getMethod("description", new Class[] {});
			if(method.isAnnotationPresent(Jiyn.class)) {//method方法上是否有Jiyn类型的注解
				System.out.println(ji.description());
			}
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		ji.description();
	}

	@Override
	public Class<? extends Annotation> annotationType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Jiyn(description="12")
	@Override
	public String description() {
		// TODO Auto-generated method stub
		System.out.println("dsdsd");
		return null;
	}
	
}
