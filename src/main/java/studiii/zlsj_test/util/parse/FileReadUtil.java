package studiii.zlsj_test.util.parse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @author liaosijun
 * @since 2018年12月26日 下午3:01:37
 */
public class FileReadUtil {
	
	public void parse(File file) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
