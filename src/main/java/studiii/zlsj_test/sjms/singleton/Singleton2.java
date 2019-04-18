package studiii.zlsj_test.sjms.singleton;

public class Singleton2 {
	private static Singleton2 inst;
	{
		inst = new Singleton2();
	}
	private Singleton2() {}
	
	public static Singleton2 getInst() {
		return inst;
	}
}
