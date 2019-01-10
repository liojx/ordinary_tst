package studiii.zlsj_test.basics.thread.volatile_;

/**
 * @author liaosijun
 * @since 2019年1月8日 下午5:52:01
 */
public class VolatileFeaturesExample {
	//使用volatile声明64位的long型变量
    volatile long vl = 0L;

    public void set(long l) {
        vl = l;   //单个volatile变量的写
    }

    public void getAndIncrement () {
        vl++;    //复合（多个）volatile变量的读/写
    }

    public long get() {
        return vl;   //单个volatile变量的读
    }
    
    
    public static void main(String[] args) {
    	VolatileFeaturesExample vfe = new VolatileFeaturesExample();
		new Thread(new Runnable() {
			
			public void run() {
				for(int i = 0;i<10;i++) {
					vfe.getAndIncrement();
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			
			public void run() {
				for(int i = 0;i<10;i++) {
					vfe.getAndIncrement();
				}
			}
		}).start();
		
		System.out.println(vfe.get());
	}
    
}
