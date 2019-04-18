package studiii.zlsj_test.basics.thread.method;


public class TestJoin implements Runnable{
	private String name ;
	public TestJoin(String name ) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public void run() {
		System.out.println(this.getName());
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new TestJoin("t1"));
		Thread t2 = new Thread(new TestJoin("t2"));
		Thread t3 = new Thread(new TestJoin("t3"));
		try {
			t1.start();
			t1.join();
			t2.start();
			t2.join();
			t3.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
