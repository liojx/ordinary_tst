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
		System.out.println("i == " + i + "�ʣ�ǰ�治��true��false�����涼Ҫִ��");


		System.out.println("-------------------------false &&---------------------------------------");
		if (false && ( ++n == 101)) {
		}
		System.out.println("[&&]  n == " + n);

		System.out.println("------------------------true &&----------------------------------------");
		if (true && ( ++n == 101)) {
		}
		System.out.println("[&&]  n == " + n + "��ǰ��false������Ͳ�ִ��,��ǰ��Ϊtrue������Ҫִ��");

		System.out.println("----------------------------false|------------------------------------");
		int j = 0;
		int k = 0;
		if (false| (j++ == 2)) { // ����ǰ��true or false �����涼ִ��
		}
		System.out.println("j=" + j);

		System.out.println("-----------------------------true|-----------------------------------");
		if (true | (k++ == 2)) { // ����ǰ��true or false �����涼ִ��
		}
		System.out.println("k=" + k +",����ǰ��true or false �����涼ִ��");
		System.out.println("-----------------------------false||-----------------------------------");
		int jj = 0;
		int kk = 0;
		if (false|| (jj++ == 2)) { // ���ǰ����false ������Ҫִ��
		}
		System.out.println("jj=" + jj);
		System.out.println("------------------------------true||----------------------------------");

		if (true || (kk++ == 2)) {  // ���ǰ����true �Ͷ�·�ˡ�
		}
		System.out.println("kk=" + kk + ",���ǰ����false ������Ҫִ��,���ǰ����true �Ͷ�·��");
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
