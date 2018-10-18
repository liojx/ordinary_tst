package studiii.zlsj_test.cdBankPeriod.tools;

import java.util.Random;
import java.util.Scanner;

public class GuessNumber {

	public static void main(String[] args) {
		
		GuessNumber n = new GuessNumber();
		n.geuss();
		
		
		
		
		
		
		
	}
	
	public void geuss(){
		Random r = new Random();
		float numf = (r.nextFloat()) * 100;
		int num = (int)numf;
		Scanner sc = new Scanner(System.in);
		int i = 6;
		int z = 0;
		System.out.println("请猜0-100里面的数，只有"+i+"次机会：");
		while(i>0){
			z = sc.nextInt();
			// System.out.print("");
			if(z>num){
				System.out.println("大了");
				i--;
			}else if(z<num){
				System.out.println("小了");
				i--;
			}else{
				System.out.println("成功");
				System.exit(0);
			}
		}
		if(i<=0)
		System.out.println("你他妈是猪啊！");
		System.out.println("正确答案是："+num);System.exit(0);
	}
	
	public void shudu(){
		
		
	}
}
