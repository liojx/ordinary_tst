package studiii.zlsj_test.basic;

import java.util.Objects;

/**
 * @Author: liaosijun
 * @Time: 2019/10/10 16:38
 */
public class AndOr {

	public static void main(String[] args) {
		int i = 0;
		int n = 0;

		System.out.println("-----------------------------false &-----------------------------------");
		if (false & ( ++i == 101)) {
		}
		System.out.println("i == " + i );

		System.out.println("-----------------------------true &-----------------------------------");
		if (true & ( ++i == 101)) {
		}
		System.out.println("i == " + i + "故，前面不管true和false，后面都要执行");


		System.out.println("-------------------------false &&---------------------------------------");
		if (false && ( ++n == 101)) {
		}
		System.out.println("[&&]  n == " + n);

		System.out.println("------------------------true &&----------------------------------------");
		if (true && ( ++n == 101)) {
		}
		System.out.println("[&&]  n == " + n + "故前面false，后面就不执行,故前面为true，后面要执行");

		System.out.println("----------------------------false|------------------------------------");
		int j = 0;
		int k = 0;
		if (false| (j++ == 2)) { // 不管前面true or false ，后面都执行
		}
		System.out.println("j=" + j);

		System.out.println("-----------------------------true|-----------------------------------");
		if (true | (k++ == 2)) { // 不管前面true or false ，后面都执行
		}
		System.out.println("k=" + k +",不管前面true or false ，后面都执行");
		System.out.println("-----------------------------false||-----------------------------------");
		int jj = 0;
		int kk = 0;
		if (false|| (jj++ == 2)) { // 如果前面是false ，后面要执行
		}
		System.out.println("jj=" + jj);
		System.out.println("------------------------------true||----------------------------------");

		if (true || (kk++ == 2)) {  // 如果前面是true 就短路了。
		}
		System.out.println("kk=" + kk + ",如果前面是false ，后面要执行,如果前面是true 就短路了");
		System.out.println();
		System.out.println("==========================================================================");
		System.out.println("------------------------------true||true----------------------------------");
		if (true||true){
			System.out.println("true||true");
		}

		System.out.println("------------------------------false||true----------------------------------");
		if (false||true){
			System.out.println("false||true");
		}

		System.out.println("------------------------------true||false----------------------------------");
		if (true||false){
			System.out.println("true||false");
		}

		System.out.println("------------------------------false||false----------------------------------");
		if (false||false){
			System.out.println("false||false");
		}
		System.out.println();
		System.out.println("==========================================================================");
		System.out.println("------------------------------true|true----------------------------------");
		if (true|true){
			System.out.println("true|true");
		}

		System.out.println("------------------------------false|true----------------------------------");
		if (false|true){
			System.out.println("false|true");
		}

		System.out.println("------------------------------true|false----------------------------------");
		if (true|false){
			System.out.println("true|false");
		}

		System.out.println("------------------------------false|false----------------------------------");
		if (false|false){
			System.out.println("false|false");
		}


		System.out.println("==========================================================================");
		System.out.println("------------------------------true&&true----------------------------------");
		if (true&&true){
			System.out.println("true&&true");
		}

		System.out.println("------------------------------false&&true----------------------------------");
		if (false&&true){
			System.out.println("false&&true");
		}

		System.out.println("------------------------------true&&false----------------------------------");
		if (true&&false){
			System.out.println("true&&false");
		}

		System.out.println("------------------------------false&&false----------------------------------");
		if (false&&false){
			System.out.println("false&&false");
		}


		System.out.println("==========================================================================");
		System.out.println("------------------------------true&true----------------------------------");
		if (true&true){
			System.out.println("true&true");
		}

		System.out.println("------------------------------false&true----------------------------------");
		if (false&true){
			System.out.println("false&true");
		}

		System.out.println("------------------------------true&false----------------------------------");
		if (true&false){
			System.out.println("true&false");
		}

		System.out.println("------------------------------false&false----------------------------------");
		if (false&false){
			System.out.println("false&false");
		}
	}
}
