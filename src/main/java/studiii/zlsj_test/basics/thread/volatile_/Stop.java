package studiii.zlsj_test.basics.thread.volatile_;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ibm.icu.util.Calendar;

/**
 * @author liaosijun
 * @since 2019年1月9日 上午9:24:29
 */
public class Stop implements Runnable{
	private volatile static boolean stop;
	private int a;
	
	public Stop(int b) {
		this.a = b;
	}
	void doSomething() {
		System.out.println("做事情...");
	}
	
	void stop() {
		System.out.println("stop=="+stop);
		while(!stop) {
			doSomething();
		}
		System.out.println("停止...");
	}
	

	public void run() {
		System.out.println("a===="+a);
		if(a == 2) {
			stop = true;
		}else {
			stop();
		}
		System.out.println("a=="+a+",stop="+stop);
	}
	
	public static void main(String[] args) throws InterruptedException {
//		Stop s2 = new Stop(2);
//		Thread t2 = new Thread(s2,"xiancheng2");
//		t2.start();
//		Thread.sleep(1000);
//		Thread t1 = new Thread(new Stop(1),"xiancheng1");
//		t1.start();
		
		/**
		 * 这里想实现的功能是，倘若线程2 把 stop 的值 更改成true 了，那么另外一个线程1 从主内存读取到的值应该就是true, 就会停止运行。但实际没有停止，
		 * 解释：线程1 和线程 2 ，把共享变量stop  分别拷贝了一份在自己的工作内存里面。若线程2先执行，并且执行了读取、赋值、写回到主内存，一系列动作做完，线程1才开始读取stop的值，这里
		 * 因为线程2 的写回主内存操作，会由于硬件的缓存一致性功能，通知其他拷贝了该共享变量的缓存行无效了，那么线程1再读取stop的值时就不再是从自己的工作内存读取了，而是从主内存读取，那么
		 * 读取到的stop肯定是新值true,即可以停止了。但事实可能不是如此，如下：
		 * 		线程2 启动，拷贝stop的值，默认初值是false ，拷贝到自己的工作内存；
		 * 		线程1启动，拷贝stop的值，默认初值是false,拷贝到自己的工作内存；
		 * 		线程???
		 */
		for(int i = 1;i <= 2;i++) {
			int x = i%2==0  ? 2: 1;
			new Thread(new Stop(x)).start();
		}
		
		
	}
}
