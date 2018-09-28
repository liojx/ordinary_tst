package studiii.zlsj_test.basics.classinit;

import java.lang.reflect.Constructor;

/**
 * @author liaosijun
 * @since 2018年9月27日 下午2:09:32
 */
public class This extends ThisFather{
	
	/**
	 * 由于父类没有默认的无参构造函数，声明了两个有参构造函数，那么无参构造就失效了。我回去验证一下，通过反射
	 */
	/**
	 * @param str
	 */
	public This(String str) {
		super(str);
		// TODO Auto-generated constructor stub
	}
	
	public This() {
		System.out.println("默认构造");
	}
	public This(String a, Integer b) {
//		System.out.println();
		this(); // 调用重载的默认构造器，必须在第一行，防止异常
	}

	public This(String a, String b) {
//		super("123"); //super和this只能出现一个
		this("123",12);
	}
	public static void main(String[] args) {
		Class clazz = This.class;
		Constructor[] cons = clazz.getConstructors();
		for(Constructor c : cons) {
			System.out.println(c.getName() + "    " + c.getModifiers() + "   " + c.getParameters()[0].getType());
//			System.out.println(c.getName() + "    " + c.getModifiers()  );
		}
	}
}
