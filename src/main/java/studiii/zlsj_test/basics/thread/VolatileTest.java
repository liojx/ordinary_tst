package studiii.zlsj_test.basics.thread;

/**
 * @author liaosijun
 * @since 2018年12月24日 上午11:22:55
 */
public class VolatileTest{
	
	public static int sum = 15;
	
	void puls() {
		while(true) {
			sum ++ ;
			if(sum == 100) {
				break;
			}
		}
	}
	
	void minus() {
		while(true) {
			sum --;
			if(sum == 0) {
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		
		Thread t1 = new Thread();
		Thread t2 = new Thread();
		t1.run();
		t1.start();
	}

	
	
}
