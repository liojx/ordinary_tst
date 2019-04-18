package studiii.zlsj_test.basics.thread.thinkinjava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DaemonFromFactory implements Runnable {
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				TimeUnit.MILLISECONDS.sleep(100);// 每隔100毫秒打印当前线程信息
				System.out.println(Thread.currentThread() + " " + this);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("打断");
			}
		}
	}
	
	public static void main(String[] args) {
		
		ExecutorService exec = Executors.newCachedThreadPool(new DaemonThreadFactory());
		for(int i = 0;i<10;i ++) {
			exec.execute(new DaemonFromFactory());
		}
		System.out.println("All daemons started");
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
