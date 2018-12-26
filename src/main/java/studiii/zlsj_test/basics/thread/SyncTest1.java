package studiii.zlsj_test.basics.thread;

/**
 * @author liaosijun
 * @since 2018年12月24日 上午11:51:03
 */
public class SyncTest1 implements Runnable {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		method1();
	}

	void method1() {
		System.out.println(Thread.currentThread().getName() + "-> Method 1 start");
		try {
			System.out.println(Thread.currentThread().getName() + "-> Method 1 execute");
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "-> Method 1 end");
	}

	
}
