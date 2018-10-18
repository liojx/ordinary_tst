package studiii.zlsj_test.cdBankPeriod.tools;

public class PlusPlus {

	static int x,y;
	static int i,j;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PlusPlus.pp();
		/*
		int t = "sss".length();
		float f = 4.0f;
		Short s = 45;
		char c = 12;
		int i=012;
		int j=034;
		int k=(int)056L;
		int l=03;
		System.out.println(i);
		System.out.println(j);
		System.out.println(5>8?10.9:9);
		
		char cc = 'x';
		int in = 10;
		System.out.println(false?i:cc);
		System.out.println(false?10:cc);
		System.out.println(12>>3);
		System.out.println(34>>3);
		*/
		//System.out.println(t);
		//PlusPlus.pp2();
//		int j=0;
//		int z=0;
//		for(int i=0;i<100;i++){
//			j =j++;
//			System.out.println(j);
//		}
//		System.out.println("结果j是："+j);
		/*
		int par1 = 33;
		Integer par2 = 33;
		PlusPlus p = new PlusPlus();
		p.yesSB(par1, par2);
		System.out.println("par1="+par1);
		System.out.println("par2="+par2);
		*/
	}
	
	public static void pp(){
		int x=0;
//		y = x++ /*0*/;
//		y = ++x /*1*/;
//		x = x++ + ++x ;
		y = x++ /*0*/+ ++x /*2*/ + x++ /*2*/;
//		x = x++ /*0*/ + ++x /*2*/ + x++ /*2*/ + x++ /*3*/;
		System.out.println("x="+x);
		System.out.println("y="+y);
	}
	
	public static void pp2(){
		j = i++ + ++i + ++i;;
		System.out.println("j="+j+",i="+i);
	}
	
	public String yesSB(final int par1,final Integer par2){
		System.out.println(par1+par2);
		return par1+par2+"";
	}
}
