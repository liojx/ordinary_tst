package studiii.zlsj_test.basics.thread.synchronised;

/**
 * @Description: TODO
 * @Author: liaosijun
 * @Time: 2019/5/8 16:56
 */
public class Thr_3_2 extends Thread{
	private ThreadSync_demo_nothis td;

	public Thr_3_2(ThreadSync_demo_nothis td, String name)
	{
		this.td = td;
		this.setName(name);
	}

	public void run()
	{
		td.setUserNamePassword("U_B", "P_B");
		td.setUserNamePassword2("U_B", "P_B");
	}
	/**
	 * 这个例子证明了：多个线程持有"对象监视器"为同一个对象的前提下，同一时间只能有一个线程可以执行synchronized(非this对象x)
	 * 代码块中的代码。
	 锁非this对象具有一定的优点：如果在一个类中有很多synchronized方法，这时虽然能实现同步，但会受到阻塞，
	 从而影响效率。但如果同步代码块锁的是非this对象，则synchronized(非this对象x)代码块中的程序与同步方法是异步的，
	 不与其他锁this同步方法争抢this锁，大大提高了运行效率。
	 其实无论是方法所还是代码锁都是要以一个对象监视器来锁定，锁定的代码是同步的，锁this是当前对象，锁String是String这个对象，
	 锁Object是Object这个对象，互不干扰，如果有其它线程调用同样用到跟上面锁this、Objcet、String相同对象的方法或代码，
	 就需要等待同步，锁代码块比锁方法更加灵活。因为锁方法锁的是this 也就是当前对象,当一个线程正在调用当前这个对象的所方法时，
	 导致其它线程调用不了该对象的其它锁this的代码，也调不了所有该对象的锁方法
	 锁的是当前这个线程，针对锁的对象的这段代码或方法，一次只能一个线程运行，其它线程运行到此的话会暂停，
	 如果是执行其它非锁的则是异步的，注意这里不要被多线程搞迷糊了。单个线程执行的时候都是同步的，当这个线程被阻塞后，
	 之后的代码（锁内的和锁外的）无论什么都不会执行，只有当唤醒或者恢复正常时才会继续往下走，走完锁内的代码就会放锁，
	 然后继续走剩余的代码
	 注意一下"private String anyString = new String();"这句话，现在它是一个全局对象，
	 因此监视的是同一个对象。如果移到try里面，那么对象的监视器就不是同一个了，调用的时候自然是异步调用，可以自己试一下。
	 最后提一点，synchronized(非this对象x)，这个对象如果是实例变量的话，指的是对象的引用，
	 只要对象的引用不变，即使改变了对象的属性，运行结果依然是同步的。
	 *
	 *
	 *
	 * */
	public static void main(String[] args) {
		ThreadSync_demo_nothis td = new ThreadSync_demo_nothis();
		Thr_3_1 mt0 = new Thr_3_1(td, "尚方宝剑");
		Thr_3_2 mt1 = new Thr_3_2(td, "桃木剑");
		mt0.start();
		mt1.start();
	}
}