package studiii.zlsj_test.basics.thread.thinkinjava;

import java.util.concurrent.ThreadFactory;

public class DaemonThreadFactory implements ThreadFactory {
	/**
	 * 创建所有的线程都是后台线程，或者说是守护线程
	 */
	@Override
	public Thread newThread(Runnable arg0) {
		Thread t = new Thread(arg0);
		t.setDaemon(true);
		return t;
	}

}
