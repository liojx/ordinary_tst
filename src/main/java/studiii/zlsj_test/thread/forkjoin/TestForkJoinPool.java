package studiii.zlsj_test.thread.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @Author: liaosijun
 * @Time: 2019/11/22 11:16
 */
public class TestForkJoinPool {

	// 每个子任务处理的最大量
	private static final Integer MAX = 1000;

	static class MyForkJoinTask extends RecursiveTask<Long> {
		// 子任务开始计算的值
		private Long startValue;
		// 子任务结束计算的值
		private Long endValue;

		public MyForkJoinTask(Long startValue, Long endValue) {
			this.startValue = startValue;
			this.endValue = endValue;
		}

		@Override
		protected Long compute() {
			// 如果条件成立，说明这个任务所需要计算的数值分为足够小了
			// 可以正式进行累加计算了
			if (endValue - startValue < MAX) {
//				System.out.println("开始计算的部分：startValue = " + startValue + ";endValue = " + endValue);
				Long totalValue = 0L;
				for (Long index = this.startValue; index <= this.endValue; index++) {
					totalValue += index;
				}
				return totalValue;
			}
			// 否则再进行任务拆分，拆分成两个任务
			else {
				MyForkJoinTask subTask1 = new MyForkJoinTask(startValue, (startValue + endValue) / 2);
				subTask1.fork();
				MyForkJoinTask subTask2 = new MyForkJoinTask((startValue + endValue) / 2 + 1, endValue);
				subTask2.fork();
				return subTask1.join() + subTask2.join();
			}
		}
	}

	public static void main(String[] args) {
		Long start = System.currentTimeMillis();
		// 这是Fork/Join框架的线程池
		ForkJoinPool pool = new ForkJoinPool();
		ForkJoinTask<Long> taskFuture = pool.submit(new MyForkJoinTask(1L, 10000000000L));
		try {
			Long result = taskFuture.get();
			System.out.println("result = " + result);
			System.out.println((System.currentTimeMillis() - start) + "ms");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace(System.out);
		}

		Long a = System.currentTimeMillis();
		Long total = 0L;
		for (Long l = 0L; l <= 10000000000L ; l++) {
			total +=l;
		}
		System.out.println("result = " + total);
		System.out.println((System.currentTimeMillis() - a) + "ms");
	}
}
