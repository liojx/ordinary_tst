package studiii.zlsj_test.basics.thread.synchronised;

/**
 * @Description: TODO
 * @Author: liaosijun
 * @Time: 2019/5/8 16:03
 */
public class ThreadSync {
	/**
	 * 分割线前面两个线程交替访问，都可以进入到第一个for循环内。
	 * 分割线后面就是必须第一个线程处理完了，第二个线程才处理。
	 * @throws Exception
	 */
	public void doLongTimeTask() throws Exception
	{
		for (int i = 0; i < 10; i++)
		{
			System.out.println("nosynchronized threadName = " +
					Thread.currentThread().getName() + ", i = " + (i + 1));
		}
		System.out.println("---------------------------");
		synchronized (this)
		{
			for (int i = 0; i < 10; i++)
			{
				System.out.println("synchronized threadName = " +
						Thread.currentThread().getName() + ", i = " + (i + 1));
			}
		}
	}
}