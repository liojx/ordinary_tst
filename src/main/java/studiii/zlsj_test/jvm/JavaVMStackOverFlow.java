package studiii.zlsj_test.jvm;

/**
 * @author liaosijun
 * @since 2019年1月10日 下午4:33:31
 */
public class JavaVMStackOverFlow {
	/**
	 * 用-Xss 128K 来设置栈大小
	 */
	private int a = 0;
	
	void add() {
		a ++;
		add();
	}
	
	public static void main(String[] args) {
		JavaVMStackOverFlow jvm = new JavaVMStackOverFlow();
		try {
			jvm.add();
		}catch(Throwable e) {
			System.out.println("a=" + jvm.a);
			throw e;
		}
}

}