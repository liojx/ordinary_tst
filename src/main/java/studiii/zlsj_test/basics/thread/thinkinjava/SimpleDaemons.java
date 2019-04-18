package studiii.zlsj_test.basics.thread.thinkinjava;

import java.util.concurrent.TimeUnit;

public class SimpleDaemons implements Runnable {
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
				System.out.println(Thread.currentThread() + " " + this);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("sleep() 被打断");
			}
		}
		
	}
	
	public static void main(String[] args) {
		for(int i = 0; i<10 ; i ++) {
			Thread daemon = new Thread(new SimpleDaemons());
			daemon.setDaemon(true);
			daemon.start();
		}
		
		System.out.println("All daemons started");
		try {
			TimeUnit.MILLISECONDS.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
