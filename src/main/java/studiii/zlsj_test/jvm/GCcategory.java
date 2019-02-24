package studiii.zlsj_test.jvm;

/**
 * @author liojx
 * @since 2019年1月16日 下午2:35:39
 */
public class GCcategory {
	private static final int _1MB = 1024 * 1024;
	
	/**
	 * VM 参数：-verbose:gc -Xms20m(最小堆) -Xmx20m(最大堆) -Xmn10m(新生代) -XX:+PrintGCDetails(打印详情)
	 * -XX:SurvivorRatio=8 (伊甸区和生存区比值8：1：1)
	 */
	public static void testAllocation() {
		byte[] allocation1,allocation2,allocation3,allocation4;
		allocation1 = new byte[2 * _1MB];
		allocation2 = new byte[2 * _1MB];
		allocation3 = new byte[2 * _1MB];
		allocation4 = new byte[4 * _1MB]; // 出现一次Minor GC
		
	}
	
	public static void main(String[] args) {
		testAllocation();
	}
	/**
	 * Heap
	 PSYoungGen      total 9216K, used 7620K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
	  eden space 8192K, 93% used [0x00000000ff600000,0x00000000ffd71148,0x00000000ffe00000)
	  from space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
	  to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
	 ParOldGen       total 10240K, used 4096K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
	  object space 10240K, 40% used [0x00000000fec00000,0x00000000ff000010,0x00000000ff600000)
	 Metaspace       used 2691K, capacity 4486K, committed 4864K, reserved 1056768K
	  class space    used 295K, capacity 386K, committed 512K, reserved 1048576K
	 */
	
	// 年轻代用的是parallel scavenge 收集器
	// 老年代是用的 parallel Old 收集器
	// 我把VM的参数设置一下：-XX:+UseConcMarkSweepGC 表示用ParNew + CMS + Serial Old 组合搜集器，结果就变成下面的样子
	/**
	 * [GC (Allocation Failure) [ParNew: 7456K->723K(9216K), 0.0262873 secs] 7456K->6869K(19456K), 0.0277359 secs] [Times: user=0.00 sys=0.00, real=0.03 secs] 
		Heap
		 par new generation   total 9216K, used 4901K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
		  eden space 8192K,  51% used [0x00000000fec00000, 0x00000000ff014930, 0x00000000ff400000)
		  from space 1024K,  70% used [0x00000000ff500000, 0x00000000ff5b4c50, 0x00000000ff600000)
		  to   space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
		 concurrent mark-sweep generation total 10240K, used 6146K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
		 Metaspace       used 2692K, capacity 4486K, committed 4864K, reserved 1056768K
		  class space    used 295K, capacity 386K, committed 512K, reserved 1048576K

	 */
}
