package studiii.zlsj_test.cdBankPeriod.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ParseScript2Normal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*File file =  new File("D:/temp/temp1.txt");
		if(!file.exists()){
			System.out.println("文件["+file.getName()+"]不存在！");
		}else{
			try {
				FileReader reader = new FileReader(file);
				BufferedReader bufferedReader =  new BufferedReader(reader);
				StringBuffer content = new StringBuffer();
				while(bufferedReader.read()!=-1){
					content = content.append(bufferedReader.readLine());
				}
				ArrayList<String> array = new ArrayList<String>();
				String coreContent = String.valueOf(content);
				System.out.println(coreContent);
				String[] strArr = coreContent.split(",");
				for(String str:strArr){
					array.add(str);
				}
				OutputStream out = new FileOutputStream(new File("D:/rs.xls"));
				WritableWorkbook workbook = Workbook.createWorkbook(out);
				WritableSheet ws = workbook.createSheet("result", 0);
				for(int i=0;i<array.size();i++){
					String v = array.get(i).trim();
					Pattern p = Pattern.compile("\\s{2,}"); //如果空格是>=2
					Matcher m = p.matcher(v);
					String cV = m.replaceAll("  ");
					String[] s = cV.split("  ");
					for(int j=0;j<s.length;j++){
						Label cell = new Label(j,i,s[j]);
						ws.addCell(cell);
					}
				}
				workbook.write();
				workbook.close();
				System.out.println("**************操作完成****************");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (RowsExceededException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}*/
		try {
//			ParseScript2Normal.x();
			xy();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void x() throws BiffException, IOException {
		//读取xls
		ArrayList allist = new ArrayList();
		int ind=5;
		while(ind<=5){
		String path ="d:\\temp\\xls\\logivrcallhis_"+ind+".xls";
		FileInputStream fis = new FileInputStream(new File(path)); 
		Workbook wk = null;
		wk = Workbook.getWorkbook(fis);
		Sheet sh1 = wk.getSheet(0);
		int colnums = sh1.getColumns();
		int rows = sh1.getRows();
		
		for(int i=1;i<rows;i++){
			for(int j=0;j<colnums;j++){
				Cell c = sh1.getCell(j, i);
				allist.add(c.getContents());
			}
		}
		ind++;
		}
		
		ArrayList slist = new ArrayList();
		ArrayList xlist = new ArrayList();
		
		HashMap map = new HashMap();
		String x="" ,y = "";
		for(int n =0;n<=allist.size();n++){
			if((n+1)%3==0){
//				slist.add(allist.get(n));
				 x = String.valueOf(allist.get(n));
				 xlist.add(allist.get(n-1));
				 y = String.valueOf(allist.get(n-1));
				 map.put(y, x);
			}
		}
		
		ArrayList rlist = new ArrayList();
		Set set = map.keySet();
		Iterator it = set.iterator();
		int d3 =0,h_1=0,h_2 = 0,h_3=0;
		int myd =0;
		double all = 767416d;
		double all5 = 60000d;
		
		int ct=0;
		String tempdate="";
		while(it.hasNext()){
			String key = (String)it.next();
			String value = (String) map.get(key);
			h_1 = Integer.parseInt(value);
			for(int xx=0;xx<xlist.size();xx++){
				String ob = (String)xlist.get(xx);
				if(key.equals(ob)){
					if(xx+1<=xlist.size())
					h_2=Integer.parseInt(String.valueOf((map.get(String.valueOf(xlist.get(xx+1))))));
					if(xx+2<=xlist.size())
					h_3=Integer.parseInt(String.valueOf((map.get(String.valueOf(xlist.get(xx+2))))));
					d3 = h_1+h_2+h_3;
					ct++;
					if(d3>myd){
						myd = d3;
						tempdate=key;
					}
					System.out.println("d3========="+d3 +"____"+"当前最大值："+myd+"____最大值的日期："+tempdate +"____进度："+ct/(all5-2));
				}
			}
		}
		System.out.println("最大的值："+myd);
//		HashMap rmap=new HashMap();
//		int s  = 0;
//		for(int i=0;i<slist.size()-2;i++){
//			s += Integer.parseInt(String.valueOf(slist.get(i)));
//			s += Integer.parseInt(String.valueOf(slist.get(i+1)));
//			s += Integer.parseInt(String.valueOf(slist.get(i+2)));
//			rlist.add(s);
//			s = 0;
//		}
		
//		System.out.println(rlist.size());
//		
//		for(int i =0 ;i<rlist.size();i++){
//			System.out.println(rlist.get(i));
//		}
//		
		int temp = 0 ;
		int big = 0;
		int small = 0;
		//rlist = new ArrayList();
		//rlist.add(34);rlist.add(54);rlist.add(340);rlist.add(314);
//		int max = 0;
		
//		for(int i =0 ;i<rlist.size();i++){
//			int cr = Integer.parseInt(String.valueOf(rlist.get(i)));
//			if(max < cr){
//				max = cr;
//			};
//			for(int j=rlist.size()-1;j>i;j--){
//				if(Integer.parseInt(String.valueOf(rlist.get(i)))>=Integer.parseInt(String.valueOf(rlist.get(j)))){
//					big = Integer.parseInt(String.valueOf(rlist.get(i)));
//					small = Integer.parseInt(String.valueOf(rlist.get(j)));
//					temp = big;
//					big = small;
//					small = temp;
//					rlist.set(i, big);
//					rlist.set(j, small);
//				}
//			}
//		}
//		System.out.println(max);
//		
//		for(int i =0;i<rlist.size();i++){
//			System.out.println(rlist.get(i));
//		}
	}
	
	
	
	static void xy() throws BiffException, IOException {
		//读取xls
		String path = "d:\\temp\\abc.xls";
		FileInputStream fis = new FileInputStream(new File(path)); 
		Workbook wk = null;
		wk = Workbook.getWorkbook(fis);
		Sheet sh1 = wk.getSheet(0);
		int colnums = sh1.getColumns();
		int rows = sh1.getRows();
		
		for(int i=0;i<rows;i++){
			for(int j=0;j<colnums;j++){
				Cell c = sh1.getCell(j, i);
				System.out.println(c.getContents());
			}
		}
	}
}
