package studiii.zlsj_test.cdBankPeriod.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Removelist {
	
	public static String trancode = "res";
	
	public static String path = "C:/Users/dev032990/Desktop/xiangying/" + trancode +".xml";
	
	public static String path2 = "C:/Users/dev032990/Desktop/xiangying/" + "dox" +".xml";
	
	/**
	 * @date 2017-4-27 下午04:28:39
	 * 该方法主要是 看老前置返回的字段，是不是都在接口文档中，因为看到3001这个接口文档中就有几个字段没有写上去，而实际报文中又返回了。
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Removelist.xtx();
	}
	public static void xtx(){
		try {
			File file = new File(path);
			FileInputStream is;
			
				is = new FileInputStream(file);
			
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader reader = new BufferedReader(isr);
			String line = "";
			String xmlStr = "";
			while ((line = reader.readLine()) != null) {
				xmlStr += (line + "\n");
			}
			
			File file2 = new File(path2);
			FileInputStream is2 = new FileInputStream(file2);
			InputStreamReader isr2 = new InputStreamReader(is2, "UTF-8");
			BufferedReader reader2 = new BufferedReader(isr2);
			String line2 = "";
			String xmlStr2 = "";
			List<String> listdoc = new ArrayList();
			while ((line2 = reader2.readLine()) != null) {
				
				listdoc.add(line2);
			}
			
			Document docc = DocumentHelper.parseText(xmlStr);
			Element body = (Element) docc.selectSingleNode("//body");
			Iterator<Element> it_body = body.elementIterator();
			List<String> resplist = new ArrayList();
			while(it_body.hasNext()){
				Element el = (Element) it_body.next();
				String a_name = el.attributeValue("name");
				resplist.add(a_name);
			}
			Element struct = (Element) docc.selectSingleNode("//struct[1]");
			if(struct!=null){
				Iterator<Element> stru_it = struct.elementIterator();
				while(stru_it.hasNext()){
					Element el = (Element) stru_it.next();
					String a_name = el.attributeValue("name");
					resplist.add(a_name);
				}
			}
			int j = 1;
			int k = 1;
			System.out.println("响应的字段：");
			for(String o : resplist){
				System.out.println(j+++"----"+o);
			}
			System.out.println("文档中的字段：");
			for(String o : listdoc){
				System.out.println(k+++"*****"+o);
			}
			
			int x = 1;
			for(int m=0;m<resplist.size();m++){
				for(int n=0;n<listdoc.size();n++){
					if(resplist.get(m).equals(listdoc.get(n))){
						System.out.println(x++ + "   " +resplist.get(m) +"========"+listdoc.get(n));
						continue;
					}
				}
			}
			resplist.removeAll(listdoc);
			int i = 1;
			
			for(String o : resplist){
				System.out.println(i+++"  xxx  "+o);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
