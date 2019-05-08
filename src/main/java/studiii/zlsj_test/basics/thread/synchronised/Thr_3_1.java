package studiii.zlsj_test.basics.thread.synchronised;

/**
 * @Description: TODO
 * @Author: liaosijun
 * @Time: 2019/5/8 16:56
 */
public class Thr_3_1 extends Thread{
	private ThreadSync_demo_nothis td;

	public Thr_3_1(ThreadSync_demo_nothis td, String name)
	{
		this.td = td;
		this.setName(name);
	}

	public void run()
	{
		td.setUserNamePassword("U_A", "P_A");
		td.setUserNamePassword2("U_A", "P_A");
	}
}