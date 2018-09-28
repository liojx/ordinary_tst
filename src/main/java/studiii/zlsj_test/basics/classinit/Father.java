package studiii.zlsj_test.basics.classinit;

/**
 * @author liaosijun
 * @since 2018年9月27日 下午2:38:22
 */
public class Father {
	
	private static String name = "lqj";
	
	protected String alias = "lmj";
	
	static {
		System.out.println("父类静态代码块");
	}
	
	{
		System.out.println("父类构造块");
	}
	
	public Father() {
		System.out.println("父类构造函数");
		System.out.println("父类的类变量alias =" + alias);
		alias = "我变";
	}
}	

