package studiii.zlsj_test.cdBankPeriod.tools;

public final class TestFinal {
	public TestFinal(){
		System.out.println("yes,i am a superclass");
	}
	
	void method(String par){
		System.out.println("par ="+par);
	}
	
	protected void method_pro(){
		System.out.println("protected");
	}
	
	private void method_pri(){
		System.out.println("private");
	}
	
	public void method_pub(){
		System.out.println("public");
	}
	
	public void method_pub(String cz){
		System.out.println("chongzai");
	}
	
	static int test(int t){
		t = t-2;
		System.out.println("t="+t);
		return t;
	}
	public static void main(String[] args) {
		int t = 3;
		System.out.println("before method,t = "+t);
		t = test(t);
		System.out.println("after method,t = "+t);
	}
}
