package studiii.zlsj_test.cdBankPeriod.tools;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import org.dom4j.Node;

public class SortByDate implements Comparator {

	public int compare(Object arg0, Object arg1) {
		Node a = (Node)arg0;
		Node b = (Node)arg1;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String a_time = a.selectSingleNode( "data[@name='TRANFER_TIME']/field").getText().replace(" ", "");
		String b_time = b.selectSingleNode( "data[@name='TRANFER_TIME']/field").getText().replace(" ", "");
		try {
			Date d1 = df.parse(a_time);
			Date d2 = df.parse(b_time);
			System.out.println("------------------1  "+a_time+"----"+df.format(d1) +"----"+d1.toString());
			System.out.println("------------------2  "+b_time+"----"+df.format(d2) +"----"+d2.toString());
			if(d1.after(d2)){
				return -1;
			}else if(d1.before(d2)){
				return 1;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
