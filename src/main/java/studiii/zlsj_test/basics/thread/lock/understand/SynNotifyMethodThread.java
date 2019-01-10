package studiii.zlsj_test.basics.thread.lock.understand;

/**
 * @author liaosijun
 * @since 2019年1月10日 上午10:18:51
 */
public class SynNotifyMethodThread extends Thread {
    private Object lock;

    public SynNotifyMethodThread(Object lock) {
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        Service service = new Service();
        service.synNotifyMethod(lock);
    }
}
