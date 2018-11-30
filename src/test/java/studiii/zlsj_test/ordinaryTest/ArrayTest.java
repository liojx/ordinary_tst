package studiii.zlsj_test.ordinaryTest;

import ch.qos.logback.core.net.SyslogOutputStream;

/**
 * Author: liaosijun
 * Description:
 * DateTime: 2018年9月12日 上午11:06:18
 * Modified By:
 */
public class ArrayTest {
	
	public static void main(String[] args) {
//		Integer[] a = new Integer[] {1,2,3};
//		a[a.length] =  5; //失败，数组定长
//		a[a.length] = 9;
//		a[a.length] = 14;
//		for(Integer x : a) {
//			System.out.println(x);
//		}
		
//		strConstructor1();
//		arrayCopy();
//		strConstructor2();
//		simpleTypeLength();
//		mod();
//		byteConvertForce();
//		byteToString();
//		intToString();
//		forfor();
//		leftMove();
		
		usignRigthMove();
	}
	
	static void strConstructor1() {
//		char[] charArr = {'a','b','c','中','国','人','d','e','f','g'};
//		System.out.println(new String(charArr, 4,10));
//		System.out.println(new String(charArr, 4,2));
	}
	
	//数组操作还真有点操蛋
	static void arrayCopy() {
		String[] origin1 = {"I am Chinese.I hate Japanese!","Fuck","Island nation","Rubbish"};
		String[] origin2 = {"Oh,me too.The island nation should be silent in the Pacific!","","","",""};
		String[] destination = {};
		System.arraycopy(origin1,0, origin2, 1, 4);
		for(String a : origin2) {
			System.out.println(a);
		}
	}
	
	static void strConstructor2() {
		int[] intArr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
		String str = new String(intArr,16,0);
		System.out.println(str == null);
		System.out.println(str .equals(""));
		String str2 = new String(intArr,13,0);
		System.out.println(str2);
		System.out.println(str2.equals(""));
	}
	
	static void simpleTypeLength() {
		System.out.println(Byte.SIZE);
		System.out.println(Character.SIZE);
		System.out.println(Short.SIZE);
		System.out.println(Integer.SIZE);
		System.out.println(Long.SIZE);
		System.out.println(Float.SIZE);
		System.out.println(Double.SIZE);
//		System.out.println(Boolean.);
	}
	
	static void mod() {
		System.out.println(-4%12);
		System.out.println(+8%12);
		System.out.println(+20%12);
		System.out.println(+32%12);
		System.out.println(-16%12);
		System.out.println(-700%256);
		
		System.out.println(200%20);
		System.out.println(200/20);
		System.out.println(24%10);
		System.out.println(24/10);
	}
	
	
	static void byteConvertForce() {
		System.out.println((byte) 300);  // 感觉是模 的概念加进来了，经典的时钟12点模，这里byte的模是它的范围256 ，300%256 = 44  
		System.out.println((byte) -700); // -700 % 256 =-188 256-188 = 68
		System.out.println(Integer.MAX_VALUE); // 2147483647
		
		// 下面两个输出结果一样，验证了我的说法
		System.out.println("超出int范围的值：" + (Long.parseLong(Integer.MAX_VALUE+"") + 121 ));  
		Long l = Long.parseLong(Integer.MAX_VALUE+"");
		System.out.println("Integer 的模长度 ："+l +1);
		System.out.println("超出int范围的值对模求余:"+ ((Long.parseLong(Integer.MAX_VALUE+"") + 121 ) % (2*l)));
		
		//未超出
		System.out.println("未超出int范围的值2:"+ (int)(Integer.MIN_VALUE - 333333)); //其实没超过int的取值范围,所以用下面2语句
//		System.out.println(2*l - ((((int)(Integer.MIN_VALUE - 333333)) % (2*l)))); //1
		System.out.println("未超出int范围的值2对模求余： " + ((((int)(Integer.MIN_VALUE - 333333)) % (2*l)))); //2
		
		
	}
	
	static void byteToString() {
		byte b = 40;
		System.out.println(Byte.toString(b));
	}
	static void intToString() {
		System.out.println(Integer.toString(Integer.MAX_VALUE,16));
	}
	
	static void forfor() {
//		for(int i =0; ; i++) { // while(true)
//			System.out.println(i);
//		}
		
		for(;;) { //while(true)
			System.out.println(123);
		}
	}
	
	// << 的优先级 小于+-
	static void leftMove() {
//		System.out.println((1<<2) +1);
//		System.out.println(1<<2 +1);
		Long ls = System.currentTimeMillis();
		int i = 1000000000;
		while(i>0) {
			int s = (229 << 6) + (229 << 5) + (229 <<2) ;
			i--;
		}
		Long le = System.currentTimeMillis();
		System.out.println("位移耗时：" + (le - ls));
		Long ls1 = System.currentTimeMillis();
		int j = 1000000000;
		while( j > 0) {
			int s = (229 * 100);
			j--;
		}
		Long le2 = System.currentTimeMillis();
		System.out.println("乘法耗时：" + (le2 - le2));
	}
	
	static void usignRigthMove() {
		int sq =  2 << 18 ;
		System.out.println(sq);
		System.out.println( 44444 * 52429 / sq );
		
		System.out.println("--------------");
		int i = 1234567;
		
		System.out.println( (i * 52429) >>> 19);
		System.out.println( i * (52429 >>> 19));
		
		System.out.println(52429 / 524288d );
		
		System.out.println(5000 * 65535);
	}
}
