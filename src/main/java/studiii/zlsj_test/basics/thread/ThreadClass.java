/**
 * 
 */
package studiii.zlsj_test.basics.thread;

/**
 * @author liaosijun
 * @since 2018年9月27日 上午11:48:27
 */
public class ThreadClass implements Runnable {

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i = 0;
		System.out.println(i ++);
	}
	
	public static void main(String[] args) {
		ThreadClass tc = new ThreadClass();
		tc.run();
	}

}
