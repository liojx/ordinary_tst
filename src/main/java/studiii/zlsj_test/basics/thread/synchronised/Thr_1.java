package studiii.zlsj_test.basics.thread.synchronised;

/**
 * @Description: TODO
 * @Author: liaosijun
 * @Time: 2019/5/8 16:04
 */
public class Thr_1 extends Thread {

	private ThreadSync td;

	public Thr_1(ThreadSync td, String name)
	{
		this.td = td;
		this.setName( name);
	}

	public void run()
	{
		try
		{
			td.doLongTimeTask();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * synchronized  锁住某块
	 * @param args
	 */
	public static void main(String[] args)
	{
		ThreadSync td = new ThreadSync();
		Thr_1 mt0 = new Thr_1(td, "冬瓜");
		Thr_1 mt1 = new Thr_1(td, "西瓜");
		mt0.start();
		mt1.start();
	}
}