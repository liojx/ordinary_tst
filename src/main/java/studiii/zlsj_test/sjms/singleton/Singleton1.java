package studiii.zlsj_test.sjms.singleton;

public class Singleton1 {
	private final static Singleton1 inst = new Singleton1();
	
	private Singleton1() {}
	
	public static Singleton1 getInst() {
		return inst;
	}
}
