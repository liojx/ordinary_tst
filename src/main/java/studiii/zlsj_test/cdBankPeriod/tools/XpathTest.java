package studiii.zlsj_test.cdBankPeriod.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XpathTest {
	public static void main(String[] args) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new FileInputStream(new File("D:/xpath-xml/cfx-public.xml")));
			XPathFactory xf = XPathFactory.newInstance();
			XPath xpath = xf.newXPath();
			String expression = "/*/SysHeader/*";
			NodeList nodelist;
			Node node;
			nodelist = (NodeList) xpath.evaluate(expression, doc, XPathConstants.NODESET);
			for(int i=0;i<nodelist.getLength();i++){
				node = nodelist.item(i);
				/*System.out.println("\n******************************************【节点"+(i+1)+"】");
				System.out.println("node.getNodeName()========================"+node.getNodeName());
				System.out.println("node.getNodeValue()======================="+node.getNodeValue());
				System.out.println("node.getTextContent()====================="+node.getTextContent());
				System.out.println("node.getAttributes()======================"+node.getAttributes());
				System.out.println("node.getChildNodes().getLength()=========="+node.getChildNodes().getLength());
				System.out.println("node.getFirstChild().getNodeName()========"+node.getFirstChild().getNodeName());*/
				System.out.println("node.getPrefix()=========================="+node.getPrefix());
			}
			System.out.println("");
			
			String expression_1 = "/"; //
			nodelist = (NodeList) xpath.evaluate(expression_1, doc, XPathConstants.NODESET);
			System.out.println(nodelist);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
	}
}
