package studiii.zlsj_test.cdBankPeriod.temp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;



public class TEST {
	String idno="111111111*1111111*";
	
	static String dealstr(String str){
		String x = "";
		 x = str.replaceAll("\\", "/");
		 return x;
	}
	
	public static void main(String[] args) {
//		TEST t = new TEST();
//		System.out.println(t.idno.indexOf("*"));
//		if(t.idno.indexOf("*")>0){
//			t.idno = t.idno.replaceAll("[0-9]", "X");
//		}
//		System.out.println(t.idno);
//		String xxxx = "12002";
//		System.out.println(xxxx.startsWith("2"));
//		String sx = "0000080977943300";
//		String int_earn=sx.substring(0,sx.length()-2)+"."+sx.substring(sx.length()-2); 
//		System.out.println(int_earn);
//		int n = int_earn.indexOf(".")+1;
//		System.out.println(n);
//		String st  = int_earn.substring(n);
//		System.out.println(st);
//		if(Integer.parseInt(st)==0)
//		int_earn = int_earn.substring(0,(n-1));
//		System.out.println(int_earn);
//		
//		BigDecimal bd = new BigDecimal(sx);
//		System.out.println(bd.divide(new BigDecimal(100)));
//		
//		System.out.println("3232".indexOf("x"));
//		System.out.println("33".equals(null));
//		double kk=5.1;
//		System.out.println("��ӡһ��˫�������֣�"+String.valueOf(kk));
//		
//		System.out.println("-----------�ָ�----------------");
//		String sxx = "1.80";
//		int nu = sxx.indexOf(".")+1;
//		System.out.println(nu);
//		sxx = sxx.substring(nu);
//		System.out.println(sxx);
//		System.out.println("-----------�ָ�---2-------------");
//		System.out.println("173025".substring(0,4));
//		
//		System.out.println(2%3==0);
//		
//		System.out.println(23/80d);
//		
//		System.out.println("5 1 0 9 2 1 1 9 8 8  0 		1 1 1 1 6 3 *".replace(" ", "")); //��tab�Ʊ��
//		System.out.println("5 1 0 9 2 1 1 9 8 8                   	0 1 1 1 1 6 3 *".replaceAll("\\s", ""));//��tab�Ʊ��
//		List l = new ArrayList();
//		l.add("20170302 151213");
//		l.add("20170302 152213");
//		l.add("20170302 112213");
//		l.add("20170402 112213");
//		
//		FileInputStream is;
//		try {
//			is = new FileInputStream("d:/temp/201C.xml");
//		
//		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
//		BufferedReader reader = new BufferedReader(isr);
//		String line = "";
//		String xmlStr = "";
//		while ((line = reader.readLine()) != null) {
//			xmlStr += (line + "\n");
//		}
//		Document docc = DocumentHelper.parseText(xmlStr);
//		Element root=docc.getRootElement();
//		l = root.selectNodes("//data[@name='TRANFER_INFO']/array/struct");
//		System.out.println("l.size "+l.size());
//		Collections.sort(l,new SortByDate()); //��ʱ�䵹������
//		for(int i=0;i<l.size();i++){
//			Node one = (Node) l.get(i);
//			String TRANFER_Time = one.selectSingleNode( "data[@name='TRANFER_TIME']/field").getText();
//			System.out.println(TRANFER_Time);
//		}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (DocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		int z =1;
//		System.out.println(z+=3-7);
//		int a=1,b=2,c=3,d=4;
////		System.out.println(a+=b-=c+d);//b=b-(c+d) a=a+b
		
		
	}
}
