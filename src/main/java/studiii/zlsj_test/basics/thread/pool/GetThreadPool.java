package studiii.zlsj_test.basics.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liaosijun
 * @since 2018年12月26日 上午10:54:21
 */
public class GetThreadPool {
	
	public ThreadPoolExecutor poolExecutor;
	
	public ThreadPoolExecutor getPool() {
		/**
		 *  corePoolSize:（线程池的基本大小）：当提交一个任务到线程池时，线程池会创建一个线程来执行任务，
		 *  即使其他空闲的基本线程能够执行新任务也会创建线程，等到需要执行的任务数大于线程池基本大小时就不再创建。如果调用了线程池的prestartAllCoreThreads方法，线程池会提前创建并启动所有基本线程 
		 *  workQueue:用于保存等待执行的任务的阻塞队列。可以选择以下几个阻塞队列
		 *  1.ArrayBlockingQueue：是一个基于数组结构的有界阻塞队列，此队列按 FIFO（先进先出）原则对元素进行排序。
		 *	2.LinkedBlockingQueue：一个基于链表结构的阻塞队列，此队列按FIFO （先进先出） 排序元素，吞吐量通常要高于ArrayBlockingQueue。静态工厂方法Executors.newFixedThreadPool()使用了这个队列。
		 *	3.SynchronousQueue：一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于LinkedBlockingQueue，静态工厂方法Executors.newCachedThreadPool使用了这个队列。
		 *	4.PriorityBlockingQueue：一个具有优先级得无限阻塞队列。
		 *	maximumPoolSize:（线程池最大大小）：线程池允许创建的最大线程数。如果队列满了，并且已创建的线程数小于最大线程数，则线程池会再创建新的线程执行任务。值得注意的是如果使用了无界的任务队列这个参数就没什么效果。
		 *  ThreadFactory：用于设置创建线程的工厂，可以通过线程工厂给每个创建出来的线程设置更有意义的名字，Debug和定位问题时非常有帮助
		 *  RejectedExecutionHandler（饱和策略）：当队列和线程池都满了，说明线程池处于饱和状态，那么必须采取一种策略处理提交的新任务。这个策略默认情况下是AbortPolicy，
		 *  表示无法处理新任务时抛出异常。以下是JDK1.5提供的四种策略：
		 *  1.CallerRunsPolicy：只用调用者所在线程来运行任务。
		 *	2.DiscardOldestPolicy：丢弃队列里最近的一个任务，并执行当前任务。
		 *	3.DiscardPolicy：不处理，丢弃掉。
		 *	4.当然也可以根据应用场景需要来实现RejectedExecutionHandler接口自定义策略。如记录日志或持久化不能处理的任务。
		 *	keepAliveTime（线程活动保持时间）：线程池的工作线程空闲后，保持存活的时间。所以如果任务很多，并且每个任务执行的时间比较短，可以调大这个时间，提高线程的利用率。
		 *	TimeUnit（线程活动保持时间的单位）：可选的单位有天（DAYS），小时（HOURS），分钟（MINUTES），毫秒(MILLISECONDS)，微秒(MICROSECONDS, 千分之一毫秒)和毫微秒(NANOSECONDS, 千分之一微秒)。
		 */
		ArrayBlockingQueue queue = new ArrayBlockingQueue<>(10);
		ThreadFactory factory = new ThreadFactory() {
			
			@Override
			public Thread newThread(Runnable r) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		RejectedExecutionHandler handler = new RejectedExecutionHandler() {
			
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
			}
		};
		ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 12, 100, TimeUnit.MILLISECONDS, queue, factory, handler);
		this.poolExecutor = poolExecutor;
		return this.poolExecutor;
	}
}
