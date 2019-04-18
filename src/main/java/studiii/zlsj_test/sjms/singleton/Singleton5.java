package studiii.zlsj_test.sjms.singleton;

public class Singleton5 {
	private static Singleton5 inst;
	private Singleton5() {}
	
	public static Singleton5 getInst() {
		if(inst == null) {
			synchronized (Singleton5.class) {
				inst = new Singleton5();
			}
		}
		return inst;
	}
}
