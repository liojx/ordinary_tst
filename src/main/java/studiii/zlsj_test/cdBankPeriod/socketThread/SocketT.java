package studiii.zlsj_test.cdBankPeriod.socketThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author Maple Leo
 * @date 2016年6月3日 下午3:33:30
 */
public class SocketT {
	
	public static String inetAddress = "127.0.0.1";
	
	public static Integer port = 61234;
	
	/**
	 * @date 2016年6月3日 下午3:33:17
	 */
	public void sock () {
		try {
			ServerSocket server = new ServerSocket(2464);
			
			Socket socket = server.accept();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.exit(0);
			String oneline = "";
			while((oneline = reader.readLine()) != null){
				System.out.println(oneline);
			}
			socket.getInputStream();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new SocketT().sock();
	}
}
