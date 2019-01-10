package studiii.zlsj_test.jvm;

/**
 * VM args : -Xss2m
 * @author liaosijun
 * @since 2019年1月10日 下午4:46:25
 */
public class JavaVMStackOOM {
	private void dodo() {
		while(true) {}
	}
	
	public void stackLeakByThread() {
		while(true) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					dodo();
				}
			}).start();
		}
	}
	
	public static void main(String[] args) {
		JavaVMStackOOM soom = new JavaVMStackOOM();
		soom.stackLeakByThread();
	}
}
