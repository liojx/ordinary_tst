package studiii.zlsj_test.basics.thread.thinkinjava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CashedThreadPool {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0 ;i < 5; i++) {
			exec.execute(new LiftOff());
		}
		exec.shutdown();
	}
	
}
