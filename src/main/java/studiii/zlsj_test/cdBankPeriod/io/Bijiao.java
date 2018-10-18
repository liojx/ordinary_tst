package studiii.zlsj_test.cdBankPeriod.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

public class Bijiao {

	private static String deskTopPath = "C:\\Users\\dev032990\\Desktop\\xc90.txt";

	private static int code = 3001;

	private static String projectFilePath = "D:\\workspace_63_for_Dev_In\\UnifyApp\\WebRoot\\WEB-INF\\UnifyApp\\config\\cc\\map\\map"
			+ code + ".xml";

	public static ArrayList<String> readDeskTopFile() {
		ArrayList<String> list = new ArrayList<String>();
		File file = new File(deskTopPath);
		try {
			FileInputStream is;

			is = new FileInputStream(file);

			InputStreamReader isr = new InputStreamReader(is, "GBK");
			BufferedReader reader = new BufferedReader(isr);
			String line = "";
			HashMap<String,String> map = new HashMap<String,String>();
			while ((line = reader.readLine()) != null) {
				map.put(line.split("\\s+")[0], line.split("\\s+")[1]);
			}
			Set<String> set = map.keySet();
			for(String str:set){
				list.add(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public static ArrayList<String> readProjectFile() throws IOException, DocumentException{
		ArrayList<String> list = new ArrayList<String>();
		FileInputStream is = new FileInputStream(projectFilePath);
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader reader = new BufferedReader(isr);
		String line = "";
		String xmlStr = "";
		while ((line = reader.readLine()) != null) {
			xmlStr += (line + "\n");
		}
		Document docc = DocumentHelper.parseText(xmlStr);
		Element root = docc.getRootElement();
		Element req = root.element("resp");
		Iterator<Element> it = req.elementIterator();
		HashMap<String,String> map = new HashMap<String,String>();
		while (it.hasNext()) {
			Element el = (Element) it.next();
			String a_name = el.attributeValue("name");
			String text = el.getTextTrim();
			map.put(text, a_name);
		}
		Set<String> set = map.keySet();
		for(String str:set){
			list.add(str);
		}
		return list;
	}
	
	/**
	 * 比较新大前置实际返回的字段是否是新接口文档中的字段，是否有多，是否有少？ 这个是新大前置的字段
	 * @throws IOException 
	 * @throws DocumentException 
	 * @date 2017-6-14 上午10:21:08
	 */
	static ArrayList<String> compareRespDocfield() throws IOException, DocumentException{
		// te/ww.xml是新大前置返回的报文，未映射的
		
		ArrayList<String> list = new ArrayList<String>();
		FileInputStream is = new FileInputStream("te/ww.xml");
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader reader = new BufferedReader(isr);
		String line = "";
		String xmlStr = "";
		while ((line = reader.readLine()) != null) {
			xmlStr += (line + "\n");
		}
		
		Document docc = DocumentHelper.parseText(xmlStr);
		Element root = docc.getRootElement();
		Element req = root.element("body");
		Iterator<Element> it = req.elementIterator();
		HashMap<String,String> map = new HashMap<String,String>();
		while (it.hasNext()) {
			Element el = (Element) it.next();
			String a_name = el.attributeValue("name");
			Element array_el = null;
			List<Element> struct_list = null;
			if(el!=null) array_el = el.element("array");
			if(array_el!=null) struct_list = array_el.elements("struct");
			if(struct_list!=null && struct_list.size()>0){
				for(Element struct_el : struct_list){
					Iterator<Element> struct_it = struct_el.elementIterator();
					while(struct_it.hasNext()){
						Element struct_data = (Element) struct_it.next();
						String struct_data_attr = struct_data.attributeValue("name");
						//System.out.println(struct_data_attr);
						list.add(struct_data_attr);
					}
					break;
				}
			}
			list.add(a_name);
		}
		if(list.contains("ESB_LIST"))list.remove("ESB_LIST");
//		for(String str:list){
//			System.out.println(str);
//		}
//		System.out.println(list.size());
		return list;
	}
	
	/**
	 * 这个是doc里面的字段  只有一列
	 * @date 2017-6-14 上午10:37:53
	 */
	static ArrayList<String> compareRespDocfield2() throws IOException, DocumentException{
		ArrayList<String> list = new ArrayList<String>();
		FileInputStream is = new FileInputStream("te/ddoc.xml");
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader reader = new BufferedReader(isr);
		String line = "";
		while ((line = reader.readLine()) != null) {
			if("".equals(line))continue;
			list.add(line);
		}
		return list;
	}
	
	/**
	 * 这个是doc里面的字段 3列，取第一和第三列 放入map(弃之，有可能字段重复，换成collection装list吧)
	 * @date 2017-6-16 下午02:25:02
	 */
	static Collection listMapDocfield() throws IOException,DocumentException{
//		HashMap<ArrayList<String>,ArrayList<String>> map = new HashMap<ArrayList<String>,ArrayList<String>>();
		Collection c = new ArrayList();
		ArrayList<String> newlist = new ArrayList<String>();
		ArrayList<String> oldlist = new ArrayList<String>();
		FileInputStream is = new FileInputStream("te/ddoc.xml");
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader reader = new BufferedReader(isr);
		String line = "";
		while ((line = reader.readLine()) != null) {
			String [] xs = line.split("\\s+");
			newlist.add(xs[0]);
			oldlist.add(xs.length==3?xs[2]:"" );
		}
//		map.put(newlist,oldlist);
		c.add(newlist);
		c.add(oldlist);
//		for(int i=0;i<newlist.size();i++){
//			System.out.println(newlist.get(i) +"---------"+ oldlist.get(i));
//		}
		return c;
	}
	
	static Collection listMapxml() throws IOException,DocumentException{
//		HashMap<ArrayList<String>,ArrayList<String>> map = new HashMap<ArrayList<String>,ArrayList<String>>();
		ArrayList<String> newlist = new ArrayList<String>();
		ArrayList<String> oldlist = new ArrayList<String>();
		Collection c = new ArrayList();
		String trancode = "3013";
		String filepath ="D:\\workspace_63_for_Dev_In\\UnifyApp\\WebRoot\\WEB-INF\\UnifyApp\\config\\cc\\map\\map"+trancode+".xml";
		String filepath2 = "D:\\workspace_63_for_Dev_In\\UnifyApp\\WebRoot\\WEB-INF\\UnifyApp\\config\\cfx\\map\\map"+trancode+".xml";
		File file = new File(trancode.startsWith("2")?filepath2:filepath);
		FileInputStream is = null;
		InputStreamReader isr = null;
		is = new FileInputStream(file);
		isr = new InputStreamReader(is, "UTF-8");
		BufferedReader reader = new BufferedReader(isr);
		String line = "";
		String xmlStr = "";
		while ((line = reader.readLine()) != null) {
			xmlStr += (line + "\n");
		}
		Document docc = DocumentHelper.parseText(xmlStr);
		Element root = docc.getRootElement();
		Element resp = root.element("req");
		Iterator<Element> it = resp.elementIterator();
		while (it.hasNext()) {
			Element el = (Element) it.next();
			String a_name = el.attributeValue("name"); //data name的属性值（新字段）
			String text = el.getText(); // data的文本值（老字段）
			newlist.add(a_name);
			oldlist.add(text);
		}
//		map.put(newlist,oldlist);
		c.add(newlist);
		c.add(oldlist);
//		for(int i=0;i<newlist.size();i++){
//			System.out.println(newlist.get(i) +"---------"+ oldlist.get(i));
//		}
		return c;
	}
	
	/**
	 * 新老前置的返回键值对
	 * @date 2017-6-20 下午06:15:29
	 */
	static HashMap<String,String> rsp (int n,int pp) throws IOException,DocumentException{
		HashMap<String,String> oldmap = new HashMap<String,String>();
		String p = "",ts="";
		if(n==1){
			p="te/rr_old.xml";
			ts="【老返回】,";
			
		}else{
			p="te/rr_new_"+pp+".xml";
			ts="{新返回},";
		}
		FileInputStream is = new FileInputStream(p);
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader reader = new BufferedReader(isr);
		String line = "";
		String xmlStr = "";
		while ((line = reader.readLine()) != null) {
			xmlStr += (line + "\n");
		}
		Document docc = DocumentHelper.parseText(xmlStr);
		Element root = docc.getRootElement();
		Element resp = (Element) docc.selectSingleNode("//body");
		Iterator<Element> it = resp.elementIterator();
		while(it.hasNext()){
			Element el = (Element) it.next();
			String a_name = el.attributeValue("name");
			Node d = el.selectSingleNode("field");
			String text ="";
				if(d!=null)text= d.getText();
			if(oldmap.containsKey(a_name)){
				while(true){
					System.out.println(ts+"你大爷的，有字段重复了，重复字段："+a_name);
				}
			}
			oldmap.put(a_name, text);
			Element array_el = null;
			List<Element> struct_list = null;
			if(el!=null) array_el = el.element("array");
			if(array_el!=null) struct_list = array_el.elements("struct");
			if(struct_list!=null && struct_list.size()>0){
				for(int i=0;i<1;i++){
					Element struct_el =struct_list.get(0);
					Iterator<Element> struct_it = struct_el.elementIterator();
					while(struct_it.hasNext()){
						Element struct_data = (Element) struct_it.next();
						String struct_data_attr = struct_data.attributeValue("name");
						Node dd = struct_data.selectSingleNode("field");
						String text_ ="";
							if(dd!=null)text_= dd.getText();
						if(oldmap.containsKey(struct_data_attr)){
							while(true){
								System.out.println(ts+"你大爷的，有字段重复了，重复字段："+struct_data_attr);
							}
						}
						oldmap.put(struct_data_attr, text_);
					}
				}
			}
		}
//		Set set = oldmap.keySet();
//		Iterator itt = set.iterator();
//		while(itt.hasNext()){
//			String key = (String)itt.next();
//			System.out.println("key----"+key+"    value----"+oldmap.get(key));
//		}
		return oldmap;
	}
	/**
	 * 把map的值取出来
	 * @date 2017-6-20 下午06:57:59
	 */
	static HashMap<String,String> mapfetch(String code)  throws IOException,DocumentException{
		  FileInputStream is = null;
		  InputStreamReader isr = null;
		  HashMap<String,String> map = new HashMap<String,String>();
		  //code = "3006";
		  try{
			  	/**如果交易码是以2开头的话就通过mapPath2去映射，否则即为信用卡**/
				String path = "D:\\workspace_63_for_Dev_In\\UnifyApp\\WebRoot\\WEB-INF\\UnifyApp\\config\\cc\\map\\map";
				if(null!=code && !"".equals(code)){
					if(code.startsWith("2")){
						path = "D:\\workspace_63_for_Dev_In\\UnifyApp\\WebRoot\\WEB-INF\\UnifyApp\\config\\cfx\\map\\map";
					}
				}
				File file = new File( path + code + ".xml");
				is = new FileInputStream(file);
				isr = new InputStreamReader(is, "UTF-8");
				BufferedReader reader = new BufferedReader(isr);
				String line = "";
				String xmlStr = "";
				while ((line = reader.readLine()) != null) {
					xmlStr += (line + "\n");
				}
				Document docc = DocumentHelper.parseText(xmlStr);
				Element root = docc.getRootElement();
				Element resp = root.element("resp");
				Iterator<Element> it = resp.elementIterator();
				while (it.hasNext()) {
					Element el = (Element) it.next();
					String a_name = el.attributeValue("name"); //data name的属性值（新字段）
					String text = el.getText(); // data的文本值（老字段）
					if(map.containsKey(a_name)){
						while(true){
							System.out.println("(map)你大爷的，有字段重复了，重复字段："+a_name);
						}
					}
					map.put(a_name, text);
				}
		  }catch(Exception e){
			  e.printStackTrace();
		  }
//		  Set set = map.keySet();
//			Iterator itt = set.iterator();
//			while(itt.hasNext()){
//				String key = (String)itt.next();
//				System.out.println(key+"----"+map.get(key));
//			}
		  return map;
	}
	public static void main(String[] args) {
		/*
		ArrayList<String> list1 = Bijiao.readDeskTopFile();
		ArrayList<String> list2 = null;
		try {
			list2 = Bijiao.readProjectFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list1.removeAll(list2);
		for(String str:list1){
			System.out.println(str);
		}
		*/
		/**removeAll表演**/
//		ArrayList<String> ali = new ArrayList<String>();
//		ArrayList<String> bli = new ArrayList<String>();
//		ArrayList<String> cli = new ArrayList<String>();
//		ali.add("1");ali.add("2");ali.add("3");ali.add("4");ali.add("5");ali.add("a");
//		bli.add("1");bli.add("2");bli.add("3");bli.add("4");bli.add("5");bli.add("6");bli.add("7");
//		cli.add("1");cli.add("2");cli.add("3");
//		ali.removeAll(bli);
//		ali.removeAll(cli);
//		bli.removeAll(ali);
//		System.out.println(bli);
		
//		try {
//			/***
//			 * 实际返回的字段，新文档中的字段，谁多谁少？
//			 */
//			ArrayList<String> list1 = compareRespDocfield();
//			ArrayList<String> temp_list1 = new ArrayList<String>();
//			temp_list1.addAll(list1);
//			ArrayList<String> list2 = compareRespDocfield2();
//			list1.removeAll(list2);
//			list2.removeAll(temp_list1);
//			System.out.println("实际返回："+list1);
//			System.out.println("接口文档："+list2);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		}
		
		
		/**
		 *  查看mapxxxx.xml里面的字段和新接口文档中的字段是否一致，当然是比较一一对应的那些字段
		 */
//		try {
//			Collection c1 = Bijiao.listMapDocfield();
//			Collection c2 = Bijiao.listMapxml();
//			Iterator it = c1.iterator();
//			Iterator it2 = c2.iterator();
//			ArrayList li1 = new ArrayList();
//			ArrayList li2 = new ArrayList();
//			ArrayList xl = null;
//			while(it.hasNext()){
//				xl = (ArrayList) it.next();
//				li1.add(xl);
//			}
//			while(it2.hasNext()){
//				xl = (ArrayList) it2.next();
//				li2.add(xl);
//			}
//			ArrayList doc_new = (ArrayList) li1.get(0);
//			ArrayList doc_old = (ArrayList) li1.get(1);
//			ArrayList xml_new = (ArrayList) li2.get(0);
//			ArrayList xml_old = (ArrayList) li2.get(1);
//			
//			xml_new.removeAll(doc_new);
//			xml_old.removeAll(doc_old);
//			System.out.println("xml_new-----"+xml_new);
//			System.out.println("xml_old-----"+xml_old);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		}
		
		/**
		 * 查看老前置和新前置返回的字段情况
		 */
		try {
			int pp = 3051;
			Map old = Bijiao.rsp(1,pp);
			Map newd = Bijiao.rsp(2,pp);
			Map mapd = Bijiao.mapfetch(""+pp);
			Set mapd_s = mapd.keySet();
			Set old_s = old.keySet();
			Set newd_s = newd.keySet();
			Iterator mapd_it = mapd_s.iterator();
			Iterator old_it = old_s.iterator();
			Iterator new_it = newd_s.iterator();
			ArrayList<String> list1 = new ArrayList(); 
			ArrayList<String> list2 = new ArrayList(); 
			ArrayList<String> list3 = new ArrayList(); 
			ArrayList<String> list4 = new ArrayList(); 
			ArrayList<String> list5 = new ArrayList(); 
			ArrayList<String> list6 = new ArrayList(); 
			int a=0,b=0,c=0,d=0,e=0,f=0;
			
			ArrayList<String> oldkeylist = new ArrayList();
			while(old_it.hasNext()){
				String old_key = (String)old_it.next();
				oldkeylist.add(old_key);
			}
			ArrayList<String> newkeylist = new ArrayList();
			while(new_it.hasNext()){
				String new_key = (String)new_it.next();
				newkeylist.add(new_key);
			}
			
			while(mapd_it.hasNext()){
				String key = (String)mapd_it.next();       list1.add(key);
				String value = (String) mapd.get(key);      list2.add(value);
				if( newd.containsKey(key) && old.containsKey(value)){
					if(newd.get(key).toString().equals(old.get(value).toString())){f++;continue;}
					String x = ("XXXXXXXXX内容不同的字段--->"+key + "=="+value + "       "+newd.get(key).toString() + "----"+old.get(value).toString());
					list3.add(x);
					a++;
				}else{
					if(newd.containsKey(key)){
						String y = ("新返回--"+key +"，内容---【"+newd.get(key).toString()+"】 ");
						list4.add(y);
						b++;
					}
					if(old.containsKey(value)){
						String z = ("老老返回--"+value +"，内容---【"+old.get(value).toString()+"】 ");
						list5.add(z);
						c++;
					}
				}
				
			}
			for(String s:list3){
				System.out.println(s);
			}
			for(String s:list4){
				System.out.println(s);
			}
			for(String s:list5){
				System.out.println(s);
			}
			oldkeylist.removeAll(list2);
			newkeylist.removeAll(list1);
			for(int i=0;i<oldkeylist.size();i++){
				System.out.println("不在map中的老字段有：" +oldkeylist.get(i).toString());
				d++;
			}
			for(int i=0;i<newkeylist.size();i++){
				//System.out.println("不在map中的xin字段有：" +newkeylist.get(i).toString());
				e++;
			}
			System.out.println("新前置个数："+newd.size()+"  老前置个数："+old.size()+" map匹配个数："+mapd.size());
			System.out.println("新老字段匹配内容相同的个数："+f);
			System.out.println("新老字段匹配并内容不同的个数："+a);
			System.out.println("新字段在map中有，但不和老字段匹配的个数："+b);
			System.out.println("老字段在map中有，但不和新字段匹配的个数："+c);
			System.out.println("不在map中的老字段的个数："+d);
			System.out.println("不在map中的xin字段的个数："+e);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
