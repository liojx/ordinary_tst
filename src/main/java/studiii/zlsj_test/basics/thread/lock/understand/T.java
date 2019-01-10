package studiii.zlsj_test.basics.thread.lock.understand;

/**
 * @author liaosijun
 * @since 2019年1月10日 上午10:19:21
 */
public class T {
	public static void main(String[] args) {
		// test0();
		test();
	}
	static void test0() {
		Object lock = new Object();

        ThreadA a = new ThreadA(lock);
        a.start();

        //NotifyThread notifyThread = new NotifyThread(lock);
       // notifyThread.start();

        SynNotifyMethodThread c = new SynNotifyMethodThread(lock);
        c.start();
	}
	
	static void test() {
		Clerk clerk = new Clerk();
        Producter producter = new Producter(clerk);
        Consumer consumer = new Consumer(clerk);
        Thread thread1 = new Thread(producter);
        thread1.setName("生产者");
        Thread thread2 = new Thread(consumer);
        thread2.setName("消费者");
        thread1.start();
        thread2.start();
	}
}
