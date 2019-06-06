package studiii.zlsj_test.basics.BigDecimal;

import java.math.BigDecimal;

/**
 * @Description: TODO
 * @Author: liaosijun
 * @Time: 2019/5/8 17:39
 */
public class Scale {
	private static BigDecimal zs1 = new BigDecimal(34.24894);
	private static BigDecimal zs2 = new BigDecimal(34.654);
	private static BigDecimal fs1 = new BigDecimal(-1.3445);
	private static BigDecimal fs2 = new BigDecimal(-1.9997);
	public static void main(String[] args) {
		System.out.println(zs1 + " ROUND_DOWN == " + zs1.setScale(2, BigDecimal.ROUND_DOWN)); //直接去掉多余的位数
		System.out.println(zs1 + " ROUND_UP == " + zs1.setScale(2, BigDecimal.ROUND_UP));    //跟上面，进位处理
		System.out.println(zs1 + " ROUND_CEILING == " + zs1.setScale(2, BigDecimal.ROUND_CEILING));//如果是正数，相当于BigDecimal.ROUND_UP,如果是负数，相当于BigDecimal.ROUND_DOWN
		System.out.println(zs1 + " ROUND_FLOOR == " + zs1.setScale(2, BigDecimal.ROUND_FLOOR));// 如果是正数，相当于BigDecimal.ROUND_DOWN,如果是负数，相当于BigDecimal.ROUND_HALF_UP
		System.out.println(zs1 + " ROUND_HALF_UP == " + zs1.setScale(2, BigDecimal.ROUND_HALF_UP));//四舍五入（若舍弃部分>=.5，就进位
		System.out.println(zs1 + " ROUND_HALF_DOWN == " + zs1.setScale(2, BigDecimal.ROUND_HALF_DOWN));//四舍五入（若舍弃部分>.5,就进位）
		System.out.println(zs1 + " ROUND_HALF_EVEN == " + zs1.setScale(2, BigDecimal.ROUND_HALF_EVEN));//如果舍弃部分左边的数字为偶数,如果舍弃部分左边的数字为奇数
		System.out.println(zs1 + " ROUND_UNNECESSARY == " + zs1.setScale(2, BigDecimal.ROUND_UNNECESSARY));//断言请求的操作具有精确的结果，因此不需要舍入,如果对获得精确结果的操作指定此舍入模式，则抛出ArithmeticException
	}

}