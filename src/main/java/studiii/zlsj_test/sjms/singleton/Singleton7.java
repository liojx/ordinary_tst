package studiii.zlsj_test.sjms.singleton;

public class Singleton7 {
	
	private Singleton7() {}
	
	private static class InnerInst{
		private static final Singleton7 inst = new Singleton7();
	}
	
	public static Singleton7 getInst() {
		return InnerInst.inst;
	}
}
