package studiii.zlsj_test.sjms.singleton;

public class Singleton4 {
	private static Singleton4 inst;
	private Singleton4() {}
	public static synchronized Singleton4 getInst() {
		if(inst == null) {
			inst = new Singleton4();
		}
		return inst;
	}
}
