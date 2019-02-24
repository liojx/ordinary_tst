package studiii.zlsj_test.jvm;

/**
 * 分代算法默认设置年龄达到15岁，进入老年代，对在survivor 熬过一次minor GC ,年龄即增加1岁
 * 如果达到15岁，就进入老年代，而设置15这个阈值的参数为 -XX:MaxTenuringThreshold=15
 * @author liojx
 * @since 2019年1月16日 下午4:14:59
 */
public class LongTimeSurvivorObjEnterOld {
	private static final int _1MB = 1024 * 1024;
	
	/**
	 * VM -verbose:gc -Xms20m(最小堆) -Xmx20m(最大堆) -Xmn10m(新生代) -XX:+PrintGCDetails(打印详情)
	 * -XX:SurvivorRatio=8 (伊甸区和生存区比值8：1：1)  -XX:MaxTenuringThreshold=1
	 * -XX:+PrintTenuringDistribution(输出显示在survivor空间里面有效的对象的岁数情况)
	 * 对我默认的Parallel scavenge + Parallel Old 没用，我切换成 ParNew + Serial Old
	 * 参数 -XX:+UseParNewGC
	 * VM 提醒我（Java HotSpot(TM) 64-Bit Server VM warning: Using the ParNew young collector 
	 * with the Serial old collector 
	 * is deprecated and will likely be removed in a future release）
	 */
	public static void testPretenureSizeThreshold() {
		byte[] allocation1,allocation2,allocation3;
		allocation1 = new byte[_1MB / 4]; // 256KB
		// 什么时候进入老年代取决于-XX:MaxTenuringThreshold设置
		allocation2 = new byte[4 * _1MB];
		allocation3 = new byte[4 * _1MB]; // 申请内存空间时，Eden区总共8m,不够，发生minorGC ,
		allocation3 = null;
		allocation3 = new byte[4 * _1MB];
	}
	
	public static void main(String[] args) {
		testPretenureSizeThreshold();
	}
	
	/**
	 * [GC (Allocation Failure) [ParNew
		Desired survivor size 524288 bytes, new threshold 1 (max 1)
		- age   1:     950688 bytes,     950688 total
		: 5664K->964K(9216K), 0.0032591 secs] 5664K->5060K(19456K), 0.0033090 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		[GC (Allocation Failure) [ParNew
		Desired survivor size 524288 bytes, new threshold 1 (max 1)
		: 5060K->0K(9216K), 0.0005290 secs] 9156K->5057K(19456K), 0.0005423 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		Heap
		 par new generation   total 9216K, used 4178K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
		  eden space 8192K,  51% used [0x00000000fec00000, 0x00000000ff014930, 0x00000000ff400000)
		  from space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
		  to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
		 tenured generation   total 10240K, used 5057K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
		   the space 10240K,  49% used [0x00000000ff600000, 0x00000000ffaf0510, 0x00000000ffaf0600, 0x0000000100000000)
		 Metaspace       used 2695K, capacity 4486K, committed 4864K, reserved 1056768K
		  class space    used 295K, capacity 386K, committed 512K, reserved 1048576K
		Java HotSpot(TM) 64-Bit Server VM warning: Using the ParNew young collector with the Serial old collector is deprecated and will likely be removed in a future release

	 */
	
	// 更改成15
	/**
	 * [GC (Allocation Failure) [ParNew
		Desired survivor size 524288 bytes, new threshold 1 (max 15)
		- age   1:     951784 bytes,     951784 total
		: 5664K->976K(9216K), 0.0024032 secs] 5664K->5072K(19456K), 0.0024315 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		[GC (Allocation Failure) [ParNew
		Desired survivor size 524288 bytes, new threshold 15 (max 15)
		: 5072K->0K(9216K), 0.0007824 secs] 9168K->5069K(19456K), 0.0007952 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		Heap
		 par new generation   total 9216K, used 4178K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
		  eden space 8192K,  51% used [0x00000000fec00000, 0x00000000ff014930, 0x00000000ff400000)
		  from space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
		  to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
		 tenured generation   total 10240K, used 5069K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
		   the space 10240K,  49% used [0x00000000ff600000, 0x00000000ffaf3600, 0x00000000ffaf3600, 0x0000000100000000)
		 Metaspace       used 2693K, capacity 4486K, committed 4864K, reserved 1056768K
		  class space    used 295K, capacity 386K, committed 512K, reserved 1048576K
		Java HotSpot(TM) 64-Bit Server VM warning: Using the ParNew young collector with the Serial old collector is deprecated and will likely be removed in a future release

	 */
	
	// 貌似和书上不一样，没有看出from 区的区别，按理15的情况，from 应该被占用的
}
