package studiii.zlsj_test.basics.thread.lock.understand;

/**
 * @author liaosijun
 * @since 2019年1月10日 上午10:17:57
 */
public class ThreadA extends Thread {
    private Object lock;

    public ThreadA(Object lock) {
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        Service service = new Service();
        service.testMethod(lock);
    }
}

