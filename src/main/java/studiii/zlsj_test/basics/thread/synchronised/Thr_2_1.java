package studiii.zlsj_test.basics.thread.synchronised;

/**
 * @Description: TODO
 * @Author: liaosijun
 * @Time: 2019/5/8 16:21
 */
public class Thr_2_1 extends Thread {
	private ThreadSync_Serv td;

	public Thr_2_1(ThreadSync_Serv td, String name)
	{
		this.td = td;
		this.setName(name);
	}

	public void run()
	{
		td.serviceMethodB();
	}

	/**
	 * 如果线程1访问了一个对象A方法的synchronized块，那么线程B对同一对象B方法的synchronized块的访问将被阻塞
	 *
	 * 看到对于serviceMethodB()方法synchronized块的访问必须等到对于serviceMethodA()方法synchronized块的访问结束之后。
	 * 那其实这个例子，
	 * 我们也可以得出一个结论：synchronized块获得的是一个对象锁，换句话说，synchronized块锁定的是整个对象。
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadSync_Serv td = new ThreadSync_Serv();
//		Thr_2_1 mt1 = new Thr_2_1(td, "氮气");
		Thr_2_0 mt0 = new Thr_2_0(td, "空气");
		Thr_2_2 mt2= new Thr_2_2(td, "氢气");
//		mt1.start();
		// 既然上面得到了一个结论synchronized块获得的是对象锁，那么如果线程1访问了一个对象方法A的synchronized块，
		// 线程2对于同一对象同步方法C的访问应该是会被阻塞的，
		// 因为线程2访问同一对象的同步方法C的时候将会尝试去获取这个对象的对象锁，但这个锁却在线程1这里
		// 可以尝试把C方法的synchronized 取消，看看运行结果就不是同步的了。
		mt0.start();
		mt2.start();
	}
}