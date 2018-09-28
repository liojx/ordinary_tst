package studiii.zlsj_test.basics.classinit;

/**
 * @author liaosijun
 * @since 2018年9月27日 下午2:41:06
 */
public class Son extends Father {
	
	private static String name = "ldy";
	
	
	private static Integer money = 100;
	
	
	static {
		money = money - 100;
		System.out.println("子类静态代码块");
	}
	
	{
		System.out.println("子类构造块");
	}
	
	public Son() {
		System.out.println("子类构造函数");
		System.out.println("子类构造后取父类变量 alias =" + alias);
	}
	public static void main(String[] args) {
		
		/**
		 * 父类静态代码块
		 * 子类静态代码块
		 * 父类构造块
		 * 父类构造函数
		 * 父类的类变量alias =lmj
		 * 子类构造块
		 * 子类构造函数
		 * 子类构造取父类变量 alias =我变
		 * money ==500	
		 */
		Father son = new Son();
		
		/***
		 * 这里static 优先于main方法，所以先是money 赋值 100，再执行第一static里面的，然后接着执行后面那个static,再执行main方法，
		 * 所以这里money的结果是500
		 */
		money = money + 200;
		System.out.println("money =="+money);
		
		
	}
	static  {
		money = money + 300;
	}
	/**
	 *   1.初始化时，执行顺序的优先级别 
	 *   		No 1 ==>> 父类声明的static的（不管静态代码块、方法、变量），都是最先的
	 *   		No 2 ==>> 子类声明的static的（不管静态代码块、方法、变量），此优先的
	 *   		No 3 ==>> 静态的main()方法
	 *   		No 4 ==>> 父类构造代码块
	 *   		No 5 ==>> 父类的类变量   类变量可以放在构造函数后面声明，然后构造函数里也能调用类变量，但类变量放在构造块后面声明，然后构造块调用就会编译出错。所以类变量初始化肯定在构造函数之前，和构造块平级吧？
	 *   		No 6 ==>> 父类构造函数
	 *   		No 7 ==>> 子类构造代码块
	 *   		No 8 ==>> 子类的类变量
	 *   		No 9 ==>> 子类构造函数
	 *   2.执行次数
	 *   		No 1 ==>> static 的是类加载就放到静态区了，所以只执行一次
	 *   		No 2 ==>> 构造块和构造函数，每次创建对象时都会调用一次。
	 *   3.类加载是把类模板装到JVM中去，初始化和类加载是两码事。方法是在编译时就有了。
	 */
}
