package studiii.zlsj_test.basics.thread.synchronised;

/**
 * @Description: TODO
 * @Author: liaosijun
 * @Time: 2019/5/8 16:15
 */
public class ThreadSync_Serv {

	public void serviceMethodA()
	{
		synchronized (this)
		{
			try
			{
				int i = 5;
				while(i-- > 0) {
					Thread.sleep(500);
					System.out.println("线程" + Thread.currentThread().getName() +  "A Method");
				}
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}

		}
	}

	public void serviceMethodB()
	{
		synchronized (this)
		{
			try {
				int i = 5;
				while(i-- > 0){
					Thread.sleep(500);
					System.out.println("线程" + Thread.currentThread().getName() +  "B Method");
				}
			} catch (InterruptedException e) {

			}
		}
	}

	public synchronized void serviceMethodC(){
		int i = 5;
		while(i-- >0) {
			System.out.println("线程" + Thread.currentThread().getName() + "C sync method");
		}
	}
}