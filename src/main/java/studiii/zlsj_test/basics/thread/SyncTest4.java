package studiii.zlsj_test.basics.thread;


/**
 * @author liaosijun
 * @since 2018年12月24日 上午11:51:03
 */
public class SyncTest4 implements Runnable {
	public static int abc = 15;
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
	}

	public synchronized void xv() {
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		abc -= 2;
		System.out.println("xv abc="+abc);
	}
	public synchronized void cv() {
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		abc %= 3;
		System.out.println("cv abc="+abc);
    }

	public static synchronized void method1() {
        System.out.println(Thread.currentThread().getName() + " Method 1 start");
        try {
            System.out.println(Thread.currentThread().getName() + " Method 1 execute");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " Method 1 end");
    }

    public static synchronized void method2() {
        System.out.println(Thread.currentThread().getName() + " Method 2 start");
        try {
            System.out.println(Thread.currentThread().getName() + " Method 2 execute");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " Method 2 end");
    }
    
    public synchronized void method3() {
        System.out.println(Thread.currentThread().getName() + " Method 3 start");
        try {
            System.out.println(Thread.currentThread().getName() + " Method 3 execute");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " Method 3 end");
    }
	
	public static void main(String[] args) {
		SyncTest4 test = new SyncTest4();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				test.cv();
			}
		},"noName-2").start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				test.xv();
			}
		},"noName-1").start();
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("abc="+abc);
		
		
		new Thread(new Runnable() {
            @Override
            public void run() {
                test.method1();
            }
        },"thread1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.method2();
            }
        },"thread2").start();
        new Thread(new Runnable() {
        	@Override
        	public void run() {
        		test.method3();
        	}
        },"thread3").start();
	}
}
