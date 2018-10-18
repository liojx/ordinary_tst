package studiii.zlsj_test.cdBankPeriod.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class DomDoc_Test {
	public static void main(String[] args) {
		DomDoc_Test.loadxml();
	}
	public static void loadxml(){
		try{
			String cxp = "d:/temp/doc.xml";
			File file = new File(cxp);
			FileInputStream is = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader reader = new BufferedReader(isr);
			String line = "";
			String xmlStr = "";
			while ((line = reader.readLine()) != null) {
				xmlStr += (line + "\n");
			}
			Document docc = DocumentHelper.parseText(xmlStr);
			Element root = docc.getRootElement();
			System.out.println(root.asXML());
			List list = root.selectNodes("//data[@name='ESB_LIST']/array/struct");
			System.out.println(list);
			Object len = root.selectSingleNode("//data[@name='LOCAL_HEAD']");
			System.out.println(len);
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
