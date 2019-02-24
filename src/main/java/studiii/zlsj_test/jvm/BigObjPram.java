package studiii.zlsj_test.jvm;

/**
 * 测试大对象设置参数：-XX:PretenureSizeThreshold=多少，当设置了这个大小，大对象超过这个大小，直接
 * 丢到老年代，但是我这个收集器默认是Parallel Scanvenge 对着参数无视，不生效。所以验证这个参数，需要
 * 更改成-XX:+UseConcMarkSweepGC 表示用ParNew + CMS + Serial Old 组合搜集器，来验证
 * @author liojx
 * @since 2019年1月16日 下午3:57:03
 */
public class BigObjPram {
	private static final int _1MB = 1024 * 1024;
	
	/**
	 * VM -verbose:gc -Xms20m(最小堆) -Xmx20m(最大堆) -Xmn10m(新生代) -XX:+PrintGCDetails(打印详情)
	 * -XX:SurvivorRatio=8 (伊甸区和生存区比值8：1：1) -XX:PretenureSizeThreshold=3145728(3MB)
	 * -XX:+UseConcMarkSweepGC
	 */
	public static void testPretenureSizeThreshold() {
		byte[] allocation;
		allocation = new byte[4 * _1MB]; // 直接分配到老年代
	}
	
	public static void main(String[] args) {
		testPretenureSizeThreshold();
	}
	
	/**
	 * Heap
		 par new generation   total 9216K, used 1476K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
		  eden space 8192K,  18% used [0x00000000fec00000, 0x00000000fed710a8, 0x00000000ff400000)
		  from space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
		  to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
		 concurrent mark-sweep generation total 10240K, used 4096K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
		 Metaspace       used 2691K, capacity 4486K, committed 4864K, reserved 1056768K
		  class space    used 295K, capacity 386K, committed 512K, reserved 1048576K							
	 */
	// 可以看到上面的CMS generation total 10240K,用了4096K，刚好就是4M，这时我把-XX:+UseConcMarkSweepGC取消，再测试一下
	// 结果是下面
	
    /**
     * Heap
		 PSYoungGen      total 9216K, used 5572K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
		  eden space 8192K, 68% used [0x00000000ff600000,0x00000000ffb71128,0x00000000ffe00000)
		  from space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
		  to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
		 ParOldGen       total 10240K, used 0K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
		  object space 10240K, 0% used [0x00000000fec00000,0x00000000fec00000,0x00000000ff600000)
		 Metaspace       used 2691K, capacity 4486K, committed 4864K, reserved 1056768K
		  class space    used 295K, capacity 386K, committed 512K, reserved 1048576K

     */
	
	// 可以看出来 ParOldGen 是0% used ,根本没有进入老年代，所以这个参数对Parallel scanvenge无效
}
