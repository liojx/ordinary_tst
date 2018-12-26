package studiii.zlsj_test.basics.staticTest;

/**
 * @author liaosijun
 * @since 2018年12月21日 下午5:46:55
 */
public class Sta1 {
	
	public  static void say(String str) {
		System.out.println(str);
	}
	
	public static void main(String[] args) {
		Sta1 s = null;
		s.say("Hi");
	}
}
