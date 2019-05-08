package studiii.zlsj_test.basics.thread.synchronised;

/**
 * @Description: TODO
 * @Author: liaosijun
 * @Time: 2019/5/8 16:21
 */
public class Thr_2_2 extends Thread {
	private ThreadSync_Serv td;

	public Thr_2_2(ThreadSync_Serv td, String name)
	{
		this.td = td;
		this.setName(name);
	}

	public void run()
	{
		td.serviceMethodC();
	}
}