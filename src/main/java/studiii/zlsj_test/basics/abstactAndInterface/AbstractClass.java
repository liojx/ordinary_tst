package studiii.zlsj_test.basics.abstactAndInterface;

import java.lang.reflect.Method;

import studiii.zlsj_test.basics.CombineUtil;
import studiii.zlsj_test.basics.ModifierUtil;

/**
 * @author liaosijun
 * @since 2018年9月27日 上午11:28:58
 */
public abstract class AbstractClass {
	/**
	 * 有构造方法，没毛病，但是不能实例，匿名内部类除外
	 */
	public AbstractClass(){
		System.out.println("constructor method");
	}
	private Integer a = 13;
	public Integer b = 14;
	Integer c = 15;
	protected Integer d = 16;
	
	/**
	 * 编译器提示，如果你是abstract的抽象方法，那么修复符改成可见的，protected 和 public 里面的一种，那么default也不行
	 */
	abstract void method1();
	
	/**
	 * 加default 是不行的，但是不加是可行的，why?
	 */
	// default abstract void method2();
	
	abstract void method2();
	
	/**
	 * 普通方法
	 */
	private String method3() {
		return "abc";
	}
	/***
	 * 静态方法
	 * @param args
	 */
	public static  void method4() {
		
	};
	static {
		System.out.println(1);
	}
	public static void main(String[] args) {
		/**
		 * 匿名内部类，可以实例化抽象类
		 */
//		AbstractClass ac = new AbstractClass() {
//
//			@Override
//			void method2() {
//				// TODO Auto-generated method stub
//				
//			}};
		
//		System.out.println(ac.a);
		
		Class a = AbstractClass.class;
		Method[] ms = a.getDeclaredMethods();
		for(Method m : ms) {
			System.out.println(ModifierUtil.getModifier(m.getModifiers()) + m.getName());
		}
	}
}
