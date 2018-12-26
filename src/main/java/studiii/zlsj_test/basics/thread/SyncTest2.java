package studiii.zlsj_test.basics.thread;

/**
 * @author liaosijun
 * @since 2018年12月24日 上午11:51:03
 */
public class SyncTest2 implements Runnable {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		method2();
	}

	public  void method2() {
        System.out.println(Thread.currentThread().getName() + "-> Method 2 start");
        try {
            System.out.println(Thread.currentThread().getName() + "-> Method 2 execute");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "-> Method 2 end");
    }

	
	
	public static void main(String[] args) {
		SyncTest1 test = new SyncTest1();
		Thread t1 = new Thread(test,"t1");
		SyncTest2 test2 = new SyncTest2();
		Thread t2 = new Thread(test2,"t2");
//		t1.run();
//		t2.run();
		t1.start();
		t2.start();
	}
}
