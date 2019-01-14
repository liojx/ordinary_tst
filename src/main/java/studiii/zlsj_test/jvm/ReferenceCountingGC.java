package studiii.zlsj_test.jvm;

/**
 * @author liaosijun
 * @since 2019年1月14日 上午9:38:12
 */
public class ReferenceCountingGC {
	public Object instance = null;
	
	private static final int _1M = 1024 * 1024;
	
	private byte[] bigSize = new byte[2* _1M];
	
	public static void testGC() {
		ReferenceCountingGC objA = new ReferenceCountingGC();
		ReferenceCountingGC objB = new ReferenceCountingGC();
		objA .instance = objB;
		objB .instance = objA;
		
		objA = null;
		objB = null;
		
		System.gc();
	}
	
	public static void main(String[] args) {
		ReferenceCountingGC.testGC();
	}
}
