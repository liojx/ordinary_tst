package studiii.zlsj_test.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Author: liaosijun
 * Description:
 * DateTime: 2018年9月14日 上午10:47:07
 * Modified By:
 */
public class TestAnno {
	
	public static void main(String[] args) {
		Class clazz = DocumentDemo.class;
		Annotation[] anno = clazz.getAnnotations();
		Method[] methods =  clazz.getMethods();
		for(Method m : methods) {
			System.out.println(m.getName() + m.getModifiers() );
		}
		for(Annotation a : anno) {
			System.out.println(a.toString());
		}
		
		DocumentDemo de = new DocumentDemo() {
			
			@Override
			public Class<? extends Annotation> annotationType() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String[] value() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String[] keyV() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String[] value2() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		System.out.println(de instanceof DocumentDemo);
		
		System.out.println(de instanceof Annotation);
		
		System.out.println(de.getClass().getGenericSuperclass());
		
		System.out.println(de.annotationType());
		
		System.out.println(de.keyV());
	}
}
