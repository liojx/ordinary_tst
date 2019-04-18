package studiii.zlsj_test.sjms.singleton;

public class Singleton3 {
	private static Singleton3 inst;
	private Singleton3() {}
	public static Singleton3 getInst() {
		if(inst == null) {
			inst = new Singleton3();
		}
		return inst;
	}
}
