package studiii.zlsj_test.ordinaryTest.Integer;

/**
 * Author: liaosijun Description: DateTime: 2018年9月13日 上午11:12:37 Modified By:
 */
public class IntegerTest {
	
	public static void main(String[] args) {
		IntegerTest it = new IntegerTest();
//		it.toString(-0x10230fff);
		int i = 2147483647;
		while(i >= 65536) {
			it.toString(i);
			i --;
		}
	}
	public  String toString(int i) {
		if (i == Integer.MIN_VALUE)
			return "-2147483648";
		int size = (i < 0) ? stringSize(-i) + 1 : stringSize(i);
		char[] buf = new char[size];
		getChars(i, size, buf);
		//return new String(buf, true); // 报错是因为 Integer 和 String  都在java.lang包下面，而 这个构造函数是 default 级别的，同一个包才能调用
		String result = new String(buf);
		return "";
	}

	final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	 final static char [] DigitTens = {
		        '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
		        '1', '1', '1', '1', '1', '1', '1', '1', '1', '1',
		        '2', '2', '2', '2', '2', '2', '2', '2', '2', '2',
		        '3', '3', '3', '3', '3', '3', '3', '3', '3', '3',
		        '4', '4', '4', '4', '4', '4', '4', '4', '4', '4',
		        '5', '5', '5', '5', '5', '5', '5', '5', '5', '5',
		        '6', '6', '6', '6', '6', '6', '6', '6', '6', '6',
		        '7', '7', '7', '7', '7', '7', '7', '7', '7', '7',
		        '8', '8', '8', '8', '8', '8', '8', '8', '8', '8',
		        '9', '9', '9', '9', '9', '9', '9', '9', '9', '9',
		        } ;

		    final static char [] DigitOnes = {
		        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
		        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
		        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
		        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
		        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
		        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
		        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
		        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
		        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
		        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
		        } ;
	static void getChars(int i, int index, char[] buf) {
		int q, r;
		int charPos = index;
		char sign = 0;

		if (i < 0) {
			sign = '-';
			i = -i;
		}

		// Generate two digits per iteration
		while (i >= 65536) {
			q = i / 100;
			// really: r = i - (q * 100);
			r = i - ((q << 6) + (q << 5) + (q << 2));
			i = q;
			buf[--charPos] = DigitOnes[r];
			buf[--charPos] = DigitTens[r];
		}
		// Fall thru to fast mode for smaller numbers
		// assert(i <= 65536, i);
		for (;;) {
			//
			q = (i * 52429) >>> (16 + 3);
			r = i - ((q << 3) + (q << 1)); // r = i-(q*10) ...
			buf[--charPos] = digits[r];
			i = q;
			if (i == 0)
				break;
		}
		if (sign != 0) {
			buf[--charPos] = sign;
		}
	}

	final static int[] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE };

	// Requires positive x
	static int stringSize(int x) {
		for (int i = 0;; i++)
			if (x <= sizeTable[i])
				return i + 1;
	}

}
