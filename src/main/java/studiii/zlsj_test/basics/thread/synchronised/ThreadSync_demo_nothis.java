package studiii.zlsj_test.basics.thread.synchronised;

/**
 * @Description: TODO
 * @Author: liaosijun
 * @Time: 2019/5/8 16:55
 */
public class ThreadSync_demo_nothis {

	private String userNameParam;
	private String passwordParam;
	private String anyString = new String();
	private String anyString2 = new String();

	/**
	 * 这里两个方法同步块里面的对象一样的话（例如都是this,都是anyString,或都是anyString2），那么两个线程来调用，必须都同步
	 *  如果有一个this,一个anyString, 只要锁住的对象不一样，那么就异步执行。
	 *
	 */
	public void setUserNamePassword(String userName, String password)
	{
		try
		{
			synchronized (anyString2)
			{
				int i = 5;
				while(i-- > 0) {
					System.out.println("线程名称为：" + Thread.currentThread().getName() +
							"在 " + System.currentTimeMillis() + " 进入同步代码块 1");
					userNameParam = userName;
					Thread.sleep(500);
					passwordParam = password;
					System.out.println("线程名称为：" + Thread.currentThread().getName() +
							"在 " + System.currentTimeMillis() + " 离开同步代码块 1");
				}
			}
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	public void setUserNamePassword2(String userName, String password)
	{
		try
		{
			synchronized (anyString2)
			{
				int i = 5;
				while(i-- > 0) {
					System.out.println("线程名称为：" + Thread.currentThread().getName() +
							"在 " + System.currentTimeMillis() + " 进入同步代码块 2");
					userNameParam = userName;
					Thread.sleep(500);
					passwordParam = password;
					System.out.println("线程名称为：" + Thread.currentThread().getName() +
							"在 " + System.currentTimeMillis() + " 离开同步代码块 2");
				}
			}
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}