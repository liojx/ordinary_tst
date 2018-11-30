package studiii.zlsj_test.basics;

/**
 * @author liaosijun
 * @since 2018年11月30日 上午10:05:02
 */
public class TIJ {
	public class O1{
		int abc ;
	}
	
	public class O2{
		int abc;
	}
	
	public static void main(String[] args) {
		
		// 引用赋值，仅仅是拷贝了一份引用，原有的引用和现有的引用指向同一块内容的内容就共享了，这符合堆内存共享的性质
		TIJ tij = new TIJ();
		O1 o1 = tij.new O1();
		O1 o12 = o1;
		o12.abc = 5;
		System.out.println(o1.abc);
		System.out.println(o1);
		System.out.println(o12);
		
	}
}
