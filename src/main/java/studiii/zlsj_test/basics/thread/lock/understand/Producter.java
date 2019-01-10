package studiii.zlsj_test.basics.thread.lock.understand;

/**
 * @author liaosijun
 * @since 2019年1月10日 上午10:40:55
 */
public class Producter implements Runnable {

	 private Clerk clerk;

     public Producter(Clerk clerk) {
         this.clerk = clerk;
     }

     @Override
     public void run() {

         while (true) {

             try {
                 // 不知道什么时候生产者会来添加产品,所以用一个随机时间来让进行线程随眠,来模仿生产者来访的不定时
                 Thread.sleep((int) (Math.random() * 10) * 10);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }

             clerk.addProduct();
         }

     }

}
