package studiii.zlsj_test.sjms.singleton;

public class Singleton6 {
	private static Singleton6 inst;
	private Singleton6() {}
	
	public static Singleton6 getInst() {
		if(inst == null) {
			synchronized (Singleton6.class) {
				if(inst == null) {
					inst = new Singleton6();
				}
			}
		}
		return inst;
	}
}
