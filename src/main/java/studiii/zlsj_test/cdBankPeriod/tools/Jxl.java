package studiii.zlsj_test.cdBankPeriod.tools;

import com.google.common.collect.Maps;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.sf.jxls.transformer.Row;
import org.assertj.core.util.Lists;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Jxl {
	/**
	 * 被写入的xls
	 */
	static String fileName = "d:/temp/ktree1.xls";

	static String fileName2 = "D:/Temp/aaa.xls";
	
	static void write() {
		File file = new File(fileName);
		if(!file.exists()) try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Workbook wk = Workbook.getWorkbook(file);
			WritableWorkbook wwk = wk.createWorkbook(file, wk);
			WritableSheet wsh = wwk.getSheet(0);
			System.out.println(wsh.getRows());
			Label lb = new Label(0,wsh.getRows(),"4");
			Label lb2 = new Label(1,wsh.getRows(),"张大千");
			Label lb3 = new Label(2,wsh.getRows(),"10");
			Label lb4 = new Label(3,wsh.getRows(),"NO10");
			wsh.addCell(lb);wsh.addCell(lb2);wsh.addCell(lb3);wsh.addCell(lb4);
			wwk.write();
			wwk.close();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public static List<Map<String, String>> get() {
		File file = new File(fileName2);
//		if(!file.exists()) try {
//			file.createNewFile();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		List list = Lists.newArrayList();
		try {
			Workbook wk = Workbook.getWorkbook(file);
//			WritableWorkbook wwk = wk.createWorkbook(file, wk);
//			WritableSheet wsh = wwk.getSheet(0);
//			System.out.println(wsh.getRows());
			Sheet sheet = wk.getSheet(0);
			int all = sheet.getRows();
			for (int i = 0; i < all; i++) {
				Map<String, String> map = Maps.newHashMap();
				Cell[] cells = sheet.getRow(i);
				map.put("code", cells[0].getContents());
				map.put("val", cells[1].getContents());
				list.add(map);
			}
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}


	public static void main(String[] args) {
		Jxl.write();
	}
}
