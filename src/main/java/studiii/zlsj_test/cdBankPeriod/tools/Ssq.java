package studiii.zlsj_test.cdBankPeriod.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import studiii.zlsj_test.cdBankPeriod.dbutil.DBtool;


public class Ssq {
	static ArrayList redList = new ArrayList();
	static int [] redDan = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33};
	static int [] blueDan = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
	static int ra(){
		double num = 0;
		num = Math.random();
		return (int)(num*100/10);
	}
	
	static int ram(){
		Random rd = new Random();
		return (rd.nextInt(33)+1);
	}
	
	static int ramL(){
		Random rd = new Random();
		return(rd.nextInt(16)+1);
	}
	
	static int[] genarateRed(){
		int [] red = new int[6];
		int i = 0;
		do{
			int r = ram();
			int v = 0;
			boolean f = true;
			for(int j=0;j<6;j++){
				v = red[j];
				if(v == r){
					f = false;
					continue;
				}
			}
			if(f){
				red[i] = r;i++;
			}
		}while(i<6);
		red = sort(red);
		return red;
	}
	static int[]  sort(int [] sz){
		if(sz != null){
			int temp = 0 ;
			for(int i =0 ;i<sz.length;i++){
				for(int j=sz.length-1;j>i;j--){
					if(sz[i]>=sz[j]){
						temp = sz[i];
						sz[i] = sz[j];
						sz[j] = temp;
					}
				}
			}
		}
		return sz;
	}
	
	List generateAll(){
		
		return redList;
	}
	
	public static void main(String[] args) {
		int x = 0;
		int zero=0,one =0,tow=0,three=0,four=0,five=0,six=0,seven=0,eight=0,nine=0;
		int o1	=	0	,
		o2	=	0	,
		o3	=	0	,
		o4	=	0	,
		o5	=	0	,
		o6	=	0	,
		o7	=	0	,
		o8	=	0	,
		o9	=	0	,
		o10	=	0	,
		o11	=	0	,
		o12	=	0	,
		o13	=	0	,
		o14	=	0	,
		o15	=	0	,
		o16	=	0	,
		o17	=	0	,
		o18	=	0	,
		o19	=	0	,
		o20	=	0	,
		o21	=	0	,
		o22	=	0	,
		o23	=	0	,
		o24	=	0	,
		o25	=	0	,
		o26	=	0	,
		o27	=	0	,
		o28	=	0	,
		o29	=	0	,
		o30	=	0	,
		o31	=	0	,
		o32	=	0	,
		o33	=	0	;
		int size=100000;
		for(int i=0;i<size;i++){
			x = Ssq.ra();
			switch(x){
			case 0: zero++;break;
			case 1: one++;break;
			case 2: tow++;break;
			case 3: three++;break;
			case 4: four++;break;
			case 5: five++;break;
			case 6: six++;break;
			case 7: seven++;break;
			case 8: eight++;break;
			case 9: nine++;break;
			default:;
			}
		}
		/**
		System.out.println("zero:"+zero);
		System.out.println("one:"+one);
		System.out.println("tow:"+tow);
		System.out.println("three:"+three);
		System.out.println("four:"+four);
		System.out.println("five:"+five);
		System.out.println("six:"+six);
		System.out.println("seven:"+seven);
		System.out.println("eight:"+eight);
		System.out.println("nine:"+nine);
		System.out.println(one+tow+three+four+five+six+seven+eight+nine+zero);
		**/
		int sz = 1000000;
		int xs = 0;
		for(int i =0;i<sz;i++){
			xs = ram();
			switch(xs){
			case 1: o1++;break;
			case 2: o2++;break;
			case 3: o3++;break;
			case 4: o4++;break;
			case 5: o5++;break;
			case 6: o6++;break;
			case 7: o7++;break;
			case 8: o8++;break;
			case 9: o9++;break;
			case 10: o10++;break;
			case 11: o11++;break;
			case 12: o12++;break;
			case 13: o13++;break;
			case 14: o14++;break;
			case 15: o15++;break;
			case 16: o16++;break;
			case 17: o17++;break;
			case 18: o18++;break;
			case 19: o19++;break;
			case 20: o20++;break;
			case 21: o21++;break;
			case 22: o22++;break;
			case 23: o23++;break;
			case 24: o24++;break;
			case 25: o25++;break;
			case 26: o26++;break;
			case 27: o27++;break;
			case 28: o28++;break;
			case 29: o29++;break;
			case 30: o30++;break;
			case 31: o31++;break;
			case 32: o32++;break;
			case 33: o33++;break;
			default:;
			}
		}
		/**
		System.out.println("1 :"+o1); 
		System.out.println("2 :"+o2); 
		System.out.println("3 :"+o3); 
		System.out.println("4 :"+o4); 
		System.out.println("5 :"+o5); 
		System.out.println("6 :"+o6); 
		System.out.println("7 :"+o7); 
		System.out.println("8 :"+o8); 
		System.out.println("9 :"+o9); 
		System.out.println("10:"+o10);
		System.out.println("11:"+o11);
		System.out.println("12:"+o12);
		System.out.println("13:"+o13);
		System.out.println("14:"+o14);
		System.out.println("15:"+o15);
		System.out.println("16:"+o16);
		System.out.println("17:"+o17);
		System.out.println("18:"+o18);
		System.out.println("19:"+o19);
		System.out.println("20:"+o20);
		System.out.println("21:"+o21);
		System.out.println("22:"+o22);
		System.out.println("23:"+o23);
		System.out.println("24:"+o24);
		System.out.println("25:"+o25);
		System.out.println("26:"+o26);
		System.out.println("27:"+o27);
		System.out.println("28:"+o28);
		System.out.println("29:"+o29);
		System.out.println("30:"+o30);
		System.out.println("31:"+o31);
		System.out.println("32:"+o32);
		System.out.println("33:"+o33);
		System.out.println(o1+o2+o3+o4+o5+o6+o7+o8+o9+o10+o11+o12+o13+o14+o15+o16+o17+o18+o19+o20+o21+o22+o23+o24+o25+o26+o27+o28+o29+o30+o31+o32+o33);
		**/
		
		Ssq ss = new Ssq();
		for(int i = 0 ;i<5000000;i++){
			int[] tag = new int[7];
			int[] red = genarateRed();
			int blue = ramL();
			System.arraycopy(red, 0, tag, 0, red.length);
			tag[6] = blue;
			redList.add(tag);
		}
		ss.insert(redList);
		System.out.println("list.size="+redList.size());
		System.out.println("lan:"+ramL());
		
		
		
	}
	
	
	static void pri(int[] x){
		for(int i=0;i<x.length;i++){
			System.out.print(x[i]+" ");
		}
	}
	
	void insert(List list){
		Connection conn =DBtool.getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement pst = conn.prepareStatement("insert into ssq(r1,r2,r3,r4,r5,r6,b) values(?,?,?,?,?,?,?)");
			for(int i = 0;i<list.size();i++){
				int[] one = (int[]) list.get(i);
				pst.setInt(1, one[0]);
				pst.setInt(2, one[1]);
				pst.setInt(3, one[2]);
				pst.setInt(4, one[3]);
				pst.setInt(5, one[4]);
				pst.setInt(6, one[5]);
				pst.setInt(7, one[6]);
				pst.addBatch();
				if(i%10000 ==0){
					pst.executeBatch();
					conn.commit();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
