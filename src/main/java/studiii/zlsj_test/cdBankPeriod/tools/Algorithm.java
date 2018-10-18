package studiii.zlsj_test.cdBankPeriod.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class Algorithm {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Algorithm alg = new Algorithm();
		alg.birthRabbit();
//		alg.sushu();
//		alg.shuiXianHuaShu();
//		alg.splitZhiYinShu(2412);
//		alg.gongYueGongBei(60,40);
//		alg.moreaPlus(14, 1);
//		alg.sort();
//		alg.mokeyEatPeach();
//		alg.ballUpandDown();
//		alg.getSum();
//		alg.jiecheng(20);
//		alg.getRepeatFlag("234255");
		
	}
	/**
	 * There is a pair of rabbit.����3�º�ÿ����һ�����ӣ�С����3�º�ÿһ����Ҳ��һ�����ӣ���������ÿ���ж��ٶԣ�
	 * 1,1,2,3,5,8,13,21,34......
	 */
	public void birthRabbit(){
		System.out.println("*****************������*********************");
		int n = 1;
		for(n=1;n<=40;n++){
			System.out.println("��"+n+"���²���������="+s(n)+"���ԣ�");
		}
		
	}
	
	public int s(int n){
		if(n==1 || n==2){
			return 1;
		}else{
			return(s(n-1)+s(n-2));
		}
	}
	
	/**
	 * 1-1000������
	 */
	public void sushu(){
		System.out.println("*****************1000��������*********************");
		List<Integer> a = new ArrayList<Integer>();
		List<Integer> b = new ArrayList<Integer>();
		for(int i=3;i<=1000;i++){
			for(int j=2;j<i;j++){
				if(i%j==0){
					//System.out.println(i);
					a.add(i);
					break;
				}
			}
			b.add(i);
		}
		b.removeAll(a);
		System.out.println("1000���ڵ�������"+b);
	}
	
	/**
	 * ˮ�ɻ�������λ���������͵��ڱ��� 153 = 1+125+9 ��10000���ڵġ�
	 */
	public void shuiXianHuaShu(){
		System.out.println("*****************ˮ�ɻ���*********************");
		int a,b,c,d;
		String ss ="";
		String s = "";
		for(int i=10;i<=10000;i++){
			s = ss+i;
			switch(s.length()){
			case 2:a=Integer.parseInt(s.charAt(0)+"");b=Integer.parseInt(s.charAt(1)+"");
			//System.out.println("2λ����"+i);
			if(a*a*a+b*b*b==i){
				System.out.println("��λ����"+i);
			}
			continue;
			case 3:a=Integer.parseInt(s.charAt(0)+"");b=Integer.parseInt(s.charAt(1)+"");c=Integer.parseInt(s.charAt(2)+"");
			//System.out.println("3λ��="+i);
			if(a*a*a+b*b*b+c*c*c==i){
				System.out.println("��λ����"+i);
			}
			continue;
			case 4:a=Integer.parseInt(s.charAt(0)+"");b=Integer.parseInt(s.charAt(1)+"");c=Integer.parseInt(s.charAt(2)+"");d=Integer.parseInt(s.charAt(3)+"");
			//System.out.println("4λ��="+i);
			if(a*a*a+b*b*b+c*c*c+d*d*d==i){
				System.out.println("��λ����"+i);
			}
			continue;
			default://System.out.println(i+"��ˮ�ɻ��������Σ�");
			}
		}
		System.out.println("ԭ��ֻ��3λ������ˮ�ɻ���......");
	}
	
	/**
	 * ��һ���������ֽ�������(�������Ӧ���Ǻ���)
	 */
	public List<Integer> splitZhiYinShu(int num){
		System.out.println("*****************�ֽ�������*********************");
		int temp = num;
		List<Integer> result = new ArrayList<Integer>();
		List<Integer> zhiYinShuList = new ArrayList<Integer>();
		int count = 0;
		for(int j=2;j<num;j++){
			if(num%j==0){
				zhiYinShuList.add(j);
				count++;
			}
		}
		System.out.println(num+"������������Ϊ��"+count);
		if(count<1){
			System.out.println(num+"���Ǻ��������ֽܷ���������");
			return null;
		}else{
			System.out.println(zhiYinShuList);
			for(int i=0;i<zhiYinShuList.size();i++){
				//˼·Ϊ��num����������ȥ�ұ��������ȴ���С�Ŀ�ʼ������num������1Ϊֹ����С�ĳ����ˣ��ʹ�list�Ƴ�ȥ����������С�ģ����γ���ȥ��
				do{
					if(num%zhiYinShuList.get(i)==0){
						num=num/zhiYinShuList.get(i);
						result.add(zhiYinShuList.get(i));
					}else{
						zhiYinShuList.remove(i);
					}
				}while(num!=1);
			}
		}
		String r = "";
		for(int i=0;i<result.size();i++){
			r += i==(result.size()-1)?result.get(i):result.get(i)+"*";
		}
		System.out.println("�ֽ������� "+temp+"="+r);
		return result;
	}
	
	/**
	 * �������������Լ������С������
	 */
	@SuppressWarnings("unchecked")
	public void gongYueGongBei(int a,int b){
		System.out.println("*************���Լ������С������****************");
		/*
		 * a��b�Ĺ�������˾��ǹ�Լ�������� 40������[2,2,2,5] 30������[2,3,5]�Ĺ�������[2,5] 2*5 = 10����Լ������10
		 * ��������ȥ����������aʣ�Ĺ���������b������С������������40ȥ��40��30�Ĺ�������ʣ[2,2],���ԣ�2*2*30 = 120������������120
		 * ����30ȥ����������ʣ[3],3*40 = 120
		*/
		List<Integer> aList = splitZhiYinShu(a);
		List<Integer> bList = splitZhiYinShu(b);
		List<Integer> tList = new ArrayList<Integer>();
		if(bList!=null&&bList.size()>0)tList.addAll(bList);
		List<Integer> gongyueList = new ArrayList<Integer>();
		List<Integer> gongbeiList = new ArrayList<Integer>();
		System.out.println(a+"����������"+aList);
		System.out.println(b+"����������"+bList); 
		Iterator iterator = null;
		Iterator iterator2 = null;
		Integer o = 0;
		Integer o2 = 0;
		if(aList!=null&&aList.size()>0&&bList!=null&&bList.size()>0){
			iterator = aList.iterator();
			iterator2 = bList.iterator();
			if(aList.size()<=bList.size()){
				while(iterator.hasNext()){
					o = (Integer) iterator.next();
					for(int i=0;i<bList.size();i++){
						o2 = bList.get(i);
						if(o == o2){
							gongyueList.add(o);
							iterator.remove();
							bList.remove(i);
							break;//��Ҫ��
						}
					}
				}
			}else{
				while(iterator2.hasNext()){
					o2 = (Integer) iterator2.next();
					for(int i=0;i<aList.size();i++){
						o = aList.get(i);
						if(o == o2){
							gongyueList.add(o);
							iterator2.remove();
							aList.remove(i);
							break;//��Ҫ��
						}
					}
				}
			}
		}
		System.out.println("After tow collections compare,the same result is :" + gongyueList);
		int gongyue =1;
		for(int x = 0;x <gongyueList.size();x++){
			gongyue *= gongyueList.get(x);
		}
		Iterator it = null;
		Integer oo = 0;
		Integer oo2 = 0 ;
		if(tList.size()>0&&gongyueList.size()>0){
			 it = tList.iterator();
			 while(it.hasNext()){
				 oo = (Integer) it.next();
				 for(int i=0;i<gongyueList.size();i++){
					 oo2 = gongyueList.get(i);
					 if(oo == oo2){
						 it.remove();
						 gongyueList.remove(i);
						 break;//��Ҫ
					 }
				 }
			 }
		}
		gongbeiList = aList;
		int gongbei = 1;
		System.out.println("After tow collections minus,the result is :" + gongbeiList);
		for(int x = 0;x <gongbeiList.size();x++){
			gongbei *= gongbeiList.get(x);
		}
			gongbei *= b;
		System.out.println("The biggest gongyue number is :" + gongyue);
		System.out.println("The smallest gongbei number is :" + gongbei);
	}
	
	/**
	 * sum (a+aa+aaa+aaaa+...+a..a)
	 * @return
	 */
	public long moreaPlus(int count,int a){
		System.out.println("******************a+aa+aaa+aaaa+...**********************");
		long result = 0;
		int j = count-1;
		for(int i=1;i<=count;i++){
			result += i*a*get10Lifang(j);
			j = j-1;
		}
		System.out.println("result = " +result);
		return result;
	}
	
	public long get10Lifang(int count){
		long val = 1;
		for(int i=0;i<count;i++){
			val *= 10;
		}
		return val;
	}
	/**
	 * One ball,100 meters fall down and upspring half of 100 meters, when 50 meters fall down
	 * and upspring 25 meters ,then repeat 10 times ,tenth fallen down ,How far do the ball go through?
	 * @return
	 */
	public float ballUpandDown(){
		float result = 0f;
		int z =10;
		for(int i =1;i<=z;i++){
			result +=getFall(i);
			System.out.println("��"+i+"�ε������߹���·��Ϊ��"+getFall(i)+"�ס�");
		}
		System.out.println(z+"�ε���·��Ϊ��"+result+"��");
		return result;
	}
	
	public float getFall(int num){
		float every = 0f;
		if(num == 1 || num == 2){
			every = 100;
		}else{
			every = getFall(num-1)/2;
		}
		return every;
	}
	/**
	 * sort from small to big 
	 * sort from big to small
	 */
	public void sort () {
		List<Integer> array = new ArrayList<Integer>();
		Scanner sc = new Scanner(System.in);
		int count = 5;
		System.out.println("Please type "+ count +" numbers:");
		while(sc.hasNext()){
			int inputNum = sc.nextInt();
			array.add(inputNum);
			count--;
			if(count == 0)break;
		}
		System.out.println(array);
		int temp = 0 ;
		int big = 0;
		int small = 0;
		for(int i =0 ;i<array.size();i++){
			for(int j=array.size()-1;j>i;j--){
				if(array.get(i)>=array.get(j)){
					big = array.get(i);
					small = array.get(j);
					temp = big;
					big = small;
					small = temp;
					array.set(i, big);
					array.set(j, small);
				}
			}
		}
		System.out.println(array);
	}
	/**
	 * ���ӵ�һ��ժ��һЩ���ӣ��������1��+1�����ڶ�����������ʣ��һ����һ�������ε���ʮ��ʱ��ֻʣ1����
	 */
	public void mokeyEatPeach(){
		int sum =0;
		for(int i = 1;i<=10;i++){
			sum = huidiaoPeachNum(i);
		}
		System.out.println("The result is :"+sum);
	}
	
	public int huidiaoPeachNum(int num){
		int r = 0;
		if(num == 1){
			r = 1;
		}else{
			r = 2*huidiaoPeachNum(num-1)+2;
		}
		return r;
	}
	
	/**
	 * 2/1+3/2+5/3+8/5+...
	 */
	public float getSum(){
		float rs = 0f;
		for(int i =0;i<20;i++){
			float a = fx(i,0);
			float b = fx(i,1);
			rs += a/b;
		}
		System.out.println(rs);
		return rs;
	}
	
	public int fx(int x,int flag){
		int s = 0;
		if(flag == 0){
			if(x == 0 || x == 1){
				return s+=(2+x);
			}else{
				return fx(x-2,0)+fx(x-1,0);
			}
		}else{
			if(x == 0 || x == 1){
				return s +=(1+x);
			}else{
				return fx(x-2,1)+fx(x-1,1);
			}
		}
	}
	
	/**
	 * �׳�
	 */
	public long jiecheng(int num){
		long rs = 0;
		for(int i=1;i<=num;i++){
			rs += getJieCheng(i);
		}
		System.out.println(rs);
		return rs;
	}
	
	public long getJieCheng(int num){
		long s = 1;
		for(int i=num;i>=1;i--){
			s *=i;
		}
		System.out.println(s);
		return s;
	}
	
	
	public void sortNumbers(){
		HashMap<String,Integer> map = new HashMap<String, Integer>();
		map.put("a", 1);
		map.put("b", 2);
		map.put("c", 2);
		map.put("d", 3);
		map.put("e", 4);
		map.put("f", 5);
		
		
	}
	
	public boolean getRepeatFlag(String num){
		boolean flag = false;
		String[] arr = num.split("");
		int a=0,b=0,c=0,d=0,e=0;
		if(num.length()!=6){
			flag = false;
		}else{
			if(arr!=null&&arr.length>0){
				for(int i=1;i<arr.length;i++){
					int x =Integer.parseInt(arr[i]);
					switch (x){
						case 1:a++;break;
						case 2:b++;break;
						case 3:c++;break;
						case 4:d++;break;
						case 5:e++;break;
					}
					
				}
			}
			if(a>1||c>1||d>1||e>1){
				flag = false;
			}else if(b!=2){
				flag = false;
			}else{
				flag = true;
			}
		}
		System.out.println(flag);
		return flag;
	}
	
	public boolean get35(int n){
		boolean flag = false;
		
		return flag;
	}
	/**
	 * ��¥��N�ף�һ�ο�����һ�ף�Ҳ�������ף����ж���������
	 */
	public void palouti() {
		int n = 14;
		int a = 1;
		int b = 2;
		//21st July,2015 ���ۣ�����3����˯��
		
	}
}
