package studiii.zlsj_test.basics.thread.volatile_;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liaosijun
 * @since 2019年1月8日 上午10:59:38
 */
public class Test {
	/**
	 * 虽然volatile 能够保证可见性，并且能够阻止CPU指令重排序，但是不能够保证原子性操作。而下面main方法就是一个非原子性操作，所以最后的值始终不是10000
	 * Java 语言的原子性只保证 基本的读取和赋值，例如下面的语句：
	 * i  = 10 ; // 原子性：直接把10赋值给i，写入工作内存
	 * y = i; // 非原子性： 1,先从主存中获取i的值，2,把i的值赋值给y,然后写入工作内存
	 * x ++;	// 非原子性
	 * x = x + 1; // 非原子性， 1，读取x的值，2，执行+1,3，写入新值
	 * Java 语言用volaile 来保证可见性
	 * Java 语言的有序性是如何的？
	 *  程序次序规则：一个线程内，按照代码顺序，书写在前面的操作先行发生于书写在后面的操作
	 *	锁定规则：一个unLock操作先行发生于后面对同一个锁额lock操作
	 *	volatile变量规则：对一个变量的写操作先行发生于后面对这个变量的读操作
	 *	传递规则：如果操作A先行发生于操作B，而操作B又先行发生于操作C，则可以得出操作A先行发生于操作C
	 *	线程启动规则：Thread对象的start()方法先行发生于此线程的每个一个动作
	 *	线程中断规则：对线程interrupt()方法的调用先行发生于被中断线程的代码检测到中断事件的发生
	 *	线程终结规则：线程中所有的操作都先行发生于线程的终止检测，我们可以通过Thread.join()方法结束、Thread.isAlive()的返回值手段检测到线程已经终止执行
	 *	对象终结规则：一个对象的初始化完成先行发生于他的finalize()方法的开始
	 * volatile 的使用必须具备以下两个条件：
	 * 1）对变量的写操作不依赖于当前值
　	 * 2）该变量没有包含在具有其他变量的不变式中
　　       * 实际上，这些条件表明，可以被写入 volatile 变量的这些有效值独立于任何程序的状态，包括变量的当前状态。
　　       * 事实上，我的理解就是上面的2个条件需要保证操作是原子性操作，才能保证使用volatile关键字的程序在并发时能够正确执行。
	 */
	public volatile int inc = 0;
    
    public void increase() {
        inc++;
    }
     
    public static void main(String[] args) {
        final Test test = new Test();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
                };
            }.start();
        }
         
        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println("inc= " + test.inc);
    }
    
    /**
     * 如果想获得预期的值，可以更改成以下任意方式：
     */
    private final static class Test2 {
        public int inc = 0;
        
        public synchronized void increase() {
            inc++;
        }
        
        public static void main(String[] args) {
            final Test2 test2 = new Test2();
            for(int i=0;i<10;i++){
                new Thread(){
                    public void run() {
                        for(int j=0;j<1000;j++)
                            test2.increase();
                    };
                }.start();
            }
            
            while(Thread.activeCount()>1)  //保证前面的线程都执行完
                Thread.yield();
            System.out.println("test2.inc=" + test2.inc);
        }
    }
    
    private static class Test3 {
        public  int inc = 0;
        Lock lock = new ReentrantLock();
        
        public  void increase() {
            lock.lock();
            try {
                inc++;
            } finally{
                lock.unlock();
            }
        }
        
        public static void main(String[] args) {
            final Test3 test3 = new Test3();
            for(int i=0;i<10;i++){
                new Thread(){
                    public void run() {
                        for(int j=0;j<1000;j++)
                            test3.increase();
                    };
                }.start();
            }
            
            while(Thread.activeCount()>1)  //保证前面的线程都执行完
                Thread.yield();
            System.out.println("test3.inc=" + test3.inc);
        }
    }
    
    
    public static class Test4 {
        public  AtomicInteger inc = new AtomicInteger();
         
        public  void increase() {
            inc.getAndIncrement();
        }
        
        public static void main(String[] args) {
            final Test4 test4 = new Test4();
            for(int i=0;i<10;i++){
                new Thread(){
                    public void run() {
                        for(int j=0;j<1000;j++)
                            test4.increase();
                    };
                }.start();
            }
            
            while(Thread.activeCount()>1)  //保证前面的线程都执行完
                Thread.yield();
            System.out.println("test4.inc=" + test4.inc);
        }
    }
}
