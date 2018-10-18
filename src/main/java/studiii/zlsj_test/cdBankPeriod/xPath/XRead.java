package studiii.zlsj_test.cdBankPeriod.xPath;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.dom.DOMDocument;
import org.dom4j.io.SAXReader;


public class XRead {
	
	private static String path = "d:/temp/xx_path.xml";
	
	private static String path_out = "d:/temp/xx_path_out.xml";
	
	private static String path_xx = "d:/temp/xxx.xml";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		XRead xread = new XRead();
		xread.xread();
	}
	
	public static void xread () {
		DOMDocument doc = new DOMDocument(path_xx);
//		System.out.println(doc.asXML());
		
		SAXReader sax = new SAXReader();
		Document doc_x = null ;
		try {
			doc_x =  sax.read(new File(path_xx));
			System.out.println(doc_x.asXML());
			System.out.println();
			System.out.println("---"+doc_x.getText());
		} catch (DocumentException e) {
			e.printStackTrace();
		}
//		System.out.println(doc.asXML());
//		System.out.println(doc);
//		System.out.println(doc.getLocalName());
//		System.out.println(doc.getName());
//		System.out.println(doc.getNamespaceURI());
//		System.out.println(doc.getNodeName());
//		System.out.println(doc.getNodeTypeName());
//		System.out.println(doc.getNodeValue());
//		System.out.println(doc.getPath());
//		System.out.println(doc.getPath(Element));
//		System.out.println(doc.getPrefix());
//		System.out.println(doc.getStringValue());
//		System.out.println(doc.getText());
//		System.out.println(doc.getTextTrim());
//		Element root=doc_x.getRootElement();
//		System.out.println(root.element("start").getPath());
//		System.out.println(root.element("start").getStringValue());
//		System.out.println(root.element("start").getQName());
//		System.out.println(root.element("start").getXPathResult(0));
//		
//		System.out.println(root.element("cfx"));
//      DOMElement configElement=(DOMElement)doc.getRootElement();
//      String attach=configElement.attributeValue("attach");
//      System.out.println(attach);
		
//		BufferedInputStream bis;
//		FileOutputStream out ;
//		BufferedReader reader;
//		
//		try {
//			bis = new BufferedInputStream(new FileInputStream(new File(path)));
//			out = new FileOutputStream(new File(path_out));
//			StringBuffer xml = new StringBuffer();
//			String str = null;
//			byte[] b = new byte[1024];
//			int len = 0 ;
//			
//			reader = new BufferedReader(new FileReader(new File(path)));
//			while ((len = bis.read(b)) != -1){
//				System.out.println(new String(b));
//			}
//			System.out.println(str);
//			while((str = reader.readLine())!=null){
//				System.out.println(new String(str.getBytes("ISO-8859-1"),"UTF-8"));
//			}
//			String requestXML = xml.toString();
//			System.out.println(requestXML);
//			String reqXML = new String(requestXML.getBytes("ISO-8859-1"),"UTF-8");
//			System.out.println(reqXML);
//			Document doc = DocumentHelper.parseText(reqXML);
//			Element root = doc.getRootElement();
//			System.out.println(root.asXML());
//			String attach=root.attributeValue("attach");
//			System.out.println(attach);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		} 
	}
}
