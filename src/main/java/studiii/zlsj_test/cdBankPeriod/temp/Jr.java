package studiii.zlsj_test.cdBankPeriod.temp;

import java.util.ArrayList;
import java.util.Scanner;

public class Jr {
	static ArrayList ar = new ArrayList();
	public  synchronized static void xc(){
		for(int i=0;i<10;i++){
			System.out.println("sync--"+i);
		}
	}
	
	public static void xx(){
		for(int i=0;i<4;i++){
			System.out.println(i);
		}
	}
	public static void xxc(){
		for(int i=0;i<4;i++){
			System.out.println("Two-"+i);
		}
	}
	
	Thread th1 = new Thread();
	
	public static class Sy extends Thread {
		public void run() {
			super.run();
			Scanner sc = new Scanner(System.in);
			System.out.println("ipt a nm  : " );
			int ipt = sc.nextInt();
			int cnt=0;
			for(int i=1;i<=ipt;i++){
				if(ipt%i==0)cnt++;
			}
			if(cnt==2)
				System.out.println("The num is zs.");
			if(cnt>2)
				System.out.println("The num is hs.");
			String r = "";
			for(int i=2;i<=ipt;i++){
				if(ipt%i==0){
						ipt=ipt/i;
						r +=i+"*";
						i=1;
				}
			}
			System.out.println("The num zys:"+r);
			System.out.println("end.");
		}
	}
	
	public   static  class T_1 extends Thread{
		public T_1(String l) {
			// TODO Auto-generated constructor stub
		}

		@Override
		public synchronized void run() {
			// TODO Auto-generated method stub
			super.run();
			for(int i=0;i<5000;i++){
				System.out.println("A");ar.add("A");
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public static class T_2 extends Thread{
		public T_2(String l) {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			for(int i=0;i<5000;i++){
				try {
					Thread.sleep(1);
					System.out.println("B");ar.add("B");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
//		Sy sy =  new Sy();
//		sy.setDaemon(false);
//		sy.start();
//		
//		System.out.println(sy.getId());
//		System.out.println(sy.getName());
//		System.out.println(sy.getPriority());
		String l = "l";
		T_1 t1 = new T_1(l);
		T_2 t2 = new T_2(l);
		t1.setPriority(1);
		t2.setPriority(10);
		t1.start();
		t2.start();
		int a=0,b=0;
		for(int i=0;i<ar.size()/2;i++){
			if(ar.get(i).toString().equals("A"))a++;
			if(ar.get(i).toString().equals("B"))b++;
		}
		Thread.sleep(15000);
		 {System.out.println("a="+a+",b="+b);}
	}
}
