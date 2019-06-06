package studiii.zlsj_test.basics.thread.wxpayConcurrentMock;

import com.ibm.icu.text.SimpleDateFormat;
import org.json.JSONException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Description: TODO
 * @Author: liaosijun
 * @Time: 2019/5/6 16:50
 */
public class LatchTest {
	// 注意，此处是非线程安全的，留坑
	private final static LongAdder iCounter = new LongAdder();

	public static void main(String[] args) throws InterruptedException {
		Runnable taskTemp = new Runnable() {



			@Override
			public void run() {
				for(int i = 0; i < 1; i++) {
					// 发起请求
					String apiUrl = "http://localhost:28999/revenue/system/0/payWechat/createOrder";
					Map<String,Object> map = new HashMap<String,Object>();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");
					
//					JSONObject json = new JSONObject();
					org.json.JSONObject json = new org.json.JSONObject();
					String abc = sdf2.format(new Date());
					try {
//						json.put("accountDate", sdf.format(new Date()));
//						json.put("code", "");
//						json.put("customerName", "石典扮");
//						json.put("money", "0.01");
						json.put("openId", "oBOkVwKD1XTq22_E46F1GwXYhR-0");
//						json.put("orderNo", "12019042200000005");
//						json.put("userCode", "10502065");
						json.put("billingCode", "12019042200000005");
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("json.tostring=" + json.toString());
					HttpClient.doPost(apiUrl, json.toString());
//                    HttpClientOp.doGet("https://www.baidu.com/");
					iCounter.add(1L);
					System.out.println(System.currentTimeMillis()  /1000 + " [" + Thread.currentThread().getName() + "] iCounter = " + iCounter);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		LatchTest latchTest = new LatchTest();
		long cost = latchTest.startTaskAllInOnce(1, taskTemp);
		System.out.println("花费：" + cost/1000/1000/1000 + "s");
	}

	public long startTaskAllInOnce(int threadNums, final Runnable task) throws InterruptedException {
		final CountDownLatch startGate = new CountDownLatch(1);
		final CountDownLatch endGate = new CountDownLatch(threadNums);
		for(int i = 0; i < threadNums; i++) {
			Thread t = new Thread() {
				public void run() {
					try {
						// 使线程在此等待，当开始门打开时，一起涌入门中
						startGate.await();
						try {
							task.run();
						} finally {
							// 将结束门减1，减到0时，就可以开启结束门了
							endGate.countDown();
						}
					} catch (InterruptedException ie) {
						ie.printStackTrace();
					}
				}
			};
			t.start();
		}
		long startTime = System.nanoTime();
		System.out.println(startTime + " [" + Thread.currentThread() + "] All thread is ready, concurrent going...");
		// 因开启门只需一个开关，所以立马就开启开始门
		startGate.countDown();
		// 等等结束门开启
		endGate.await();
		long endTime = System.nanoTime();
		System.out.println(endTime + " [" + Thread.currentThread() + "] All thread is completed.");
		return endTime - startTime;
	}

	void send(){
		String apiUrl = "http://localhost:28999/revenue/system/0/payWechat/createOrder";
		StringBuilder sb = new StringBuilder();
		sb.append("accountDate");
	}
}