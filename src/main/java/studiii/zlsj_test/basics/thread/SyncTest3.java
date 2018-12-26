package studiii.zlsj_test.basics.thread;

import com.sun.jdi.Method;

/**
 * @author liaosijun
 * @since 2018年12月24日 上午11:51:03
 */
public class SyncTest3 implements Runnable {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		method3();
	}

	public synchronized void method3() {
        System.out.println(Thread.currentThread().getName() + "-> Method 3 start");
        try {
            System.out.println(Thread.currentThread().getName() + "-> Method 3 execute");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "-> Method 3 end");
    }
	public synchronized void method4() {
		System.out.println(Thread.currentThread().getName() + "-> Method 4 start");
		try {
			System.out.println(Thread.currentThread().getName() + "-> Method 4 execute");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "-> Method 4 end");
	}

	
	
	public static void main(String[] args) {
		SyncTest1 test = new SyncTest1();
		Thread t1 = new Thread(test,"t1");
		SyncTest2 test2 = new SyncTest2();
		Thread t2 = new Thread(test2,"t2");
		SyncTest3 test3 = new SyncTest3();
		Thread t3 = new Thread(test3,"t3");
//		t1.run();
//		t2.run();
//		t3.run();
//		t1.start();
//		t2.start();
//		t3.start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				test3.method3();
			}
		},"noName-2").start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				test3.method4();
			}
		},"noName-1").start();;
	}
}
