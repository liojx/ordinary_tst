package studiii.zlsj_test.basics.thread;

/**
 * @author liaosijun
 * @since 2018年12月21日 下午6:14:06
 */
public class TwoThreadTest implements Runnable{

	private int aa = 0;
	
	void plus () {
		boolean isOdd = false;
		for (int i = 1; i <= 2; ++i) {
			if (i % 2 == 1)
				isOdd = true;
			else
				isOdd = false;
			aa += i * (isOdd ? 1 : -1);
			System.out.println("i="+i+",aa="+aa);
		}
		
	}
	
	public static void main(String[] args) {
		TwoThreadTest t = new TwoThreadTest();
		Thread a = new Thread(t);
		Thread b = new Thread(t);
		a.start();
		b.start();
		System.out.println("结果："+t.aa);
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		plus();
	}
}
