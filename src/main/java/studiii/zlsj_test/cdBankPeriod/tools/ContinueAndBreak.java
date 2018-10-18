package studiii.zlsj_test.cdBankPeriod.tools;

public class ContinueAndBreak {

	public static void main(String[] args) {
		System.out.println("----continue----");
		for(int i=1;i<=10;i++){
			if(i%3==0){
				System.out.println(i);
				continue;
			}
		}
		System.out.println("----break----");
		for(int i=1;i<=10;i++){
			if(i%3==0){
				System.out.println(i);
				break;
			}
		}
	}
}
