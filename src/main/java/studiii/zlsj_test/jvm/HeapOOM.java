package studiii.zlsj_test.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liaosijun
 * @since 2019年1月10日 下午4:20:11
 */
public class HeapOOM {
	
	/**
	 * test -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
	 * @author liaosijun
	 * @since 2019年1月10日 下午4:22:47
	 */
	static class B{
		
	}
	public static void main(String[] args) {
		List<B> list = new ArrayList<B>();
		while(true) {
			list.add(new B());
		}
	}
}
