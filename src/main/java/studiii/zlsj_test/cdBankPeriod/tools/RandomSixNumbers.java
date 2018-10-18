package studiii.zlsj_test.cdBankPeriod.tools;

import java.util.Random;

public class RandomSixNumbers {
	private static int p_len = 6; //长度
	
	public static String produceSixNumbers (String cardNo){
		Random random = new Random();
		int len = cardNo.length();
		int[] cardArr = new int[len];
		for(int i=0;i<len;i++){
			cardArr[i] = Integer.parseInt(cardNo.charAt(i)+"");
		}
		String result = "";
		for(int j=0;j<p_len;j++){
			result += cardArr[random.nextInt(len)];
		}
		return result;
	}
	
	public static void main(String[] args) {
		String z = RandomSixNumbers.produceSixNumbers("1234567890");
		System.out.println(z);
	
	}

}
