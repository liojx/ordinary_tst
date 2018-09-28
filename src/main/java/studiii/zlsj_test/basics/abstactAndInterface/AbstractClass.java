package studiii.zlsj_test.basics.abstactAndInterface;

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
	
	/**
	 * 编译器提示，如果你是abstract的抽象方法，那么修复符改成可见的，protected 和 public 里面的一种，那么default也不行
	 */
//	private abstract void method1();
	
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
	
	public static void main(String[] args) {
		/**
		 * 匿名内部类，可以实例化抽象类
		 */
		AbstractClass ac = new AbstractClass() {

			@Override
			void method2() {
				// TODO Auto-generated method stub
				
			}};
		System.out.println(ac.a);
	}
}
