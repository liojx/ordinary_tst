package studiii.zlsj_test.basics;

import java.util.ArrayList;

public class EqualT {
	public static void main(String[] args) {
		
		Integer a = 1;
		Integer b = 2;
		Integer c = 3;
		Integer d = 3;
		Integer e = 321;
		Integer f = 321;
		Long g = 3L;
		System.out.println(c == d);
		System.out.println(e == f);
		System.out.println(c == (a + b));
		System.out.println(c.equals(a + b));
		System.out.println(g == (a + b));
		System.out.println(g.equals(a + b));
		
		// 0010 & 0101 = 0000
		System.out.println(2&3);
		System.out.println((2|3) );
		System.out.println(2^3);
		System.out.println(~2);
		System.out.println((2|3)^1);
		ArrayList arr = new ArrayList();
		System.out.println(Math.round(11.5));
		System.out.println(Math.round(-11.5));
	}
}
