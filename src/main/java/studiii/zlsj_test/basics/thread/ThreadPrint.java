package studiii.zlsj_test.basics.thread;

/**
 * @author liaosijun
 * @since 2018年9月27日 下午4:02:06
 */
public class ThreadPrint {
	
	
	public static void main(String[] args) {
		
		Thread[] threads = new Thread[3];
		
		for(int i = 0;i < 3;i++) {
			/**
			 * 匿名类 new 接口，实现接口方法
			 * 
			 * */
			threads[i] = new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					for(int j = 0;j<5;j++) {
						System.out.print(j);
					}
					System.out.print(" ");
				}
			});
		}
		
		for(Thread t : threads) {
			t.start();
		}
	}
}
