package studiii.zlsj_test.serializable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Author: liaosijun
 * Description:
 * DateTime: 2018年9月7日 下午4:24:17
 * Modified By:
 */
public class SerializableTestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		SerializableTestMain mn = new SerializableTestMain();
		mn.write();
		mn.read();
	}
	
	void write() {
		try {
			FileOutputStream fos = null;
			fos = new FileOutputStream("C:\\tmp\\serial.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(new SerializableTestEnty());
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void read() {
		try {
			FileInputStream fis = null;
			fis = new FileInputStream("C:\\\\tmp\\\\serial.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object obj = ois.readObject();
			System.out.println(obj instanceof SerializableTestEnty);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
