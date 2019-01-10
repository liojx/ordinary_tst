package studiii.zlsj_test.basics.thread.lock.understand;

/**
 * @author liaosijun
 * @since 2019年1月10日 上午10:38:20
 */
public class Clerk {
	// 产品数量
    int productNums = 0;

    // 增加产品数量,店员获取到的数量达到10个后，将不会继续增加。就等待消费者来取东西了
    public synchronized void addProduct() {

        if (productNums >= 10) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {

            productNums = productNums + 1;
            System.out.println(Thread.currentThread().getName() + ":"
                    + "添加了第" + productNums + "个产品");
            notify();// 产品数量没到上线，通知其他线程来进行存或则取
        }
    }

    public synchronized void getProduct() {
        if (productNums <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + ":"
                    + "买走了第" + productNums + "个产品");
            productNums = productNums - 1;
            notify();// 产品数量没到下线，通知其他线程来进行存或则取
        }

    }
}
