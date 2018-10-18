package studiii.zlsj_test.cdBankPeriod.tools;

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Jxl {
	/**
	 * 被写入的xls
	 */
	static String fileName = "d:/temp/ktree1.xls";
	
	static void write() {
		File file = new File(fileName);
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
	
	public static void main(String[] args) {
		Jxl.write();
	}
}
