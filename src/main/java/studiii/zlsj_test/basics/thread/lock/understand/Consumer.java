package studiii.zlsj_test.basics.thread.lock.understand;

/**
 * @author liaosijun
 * @since 2019年1月10日 上午10:42:16
 */
public class Consumer implements Runnable {

	 private Clerk clerk;

     public Consumer(Clerk clerk) {
         this.clerk = clerk;
     }

     @Override
     public void run() {

         while (true) {

             try {
                 // 不知道什么时候消费者回来添加产品,所以用一个随机时间来让进行线程随眠,来模仿消费者来访的不定时
                 Thread.sleep((int) (Math.random() * 10) * 10);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }

             clerk.getProduct();

         }

     }

}
