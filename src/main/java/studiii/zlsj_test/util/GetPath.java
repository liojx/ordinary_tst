package studiii.zlsj_test.util;

/**
 * @author liaosijun
 * @since 2018年12月12日 下午5:53:02
 */
public class GetPath {
	public static void main(String[] args) {
		System.out.println("当前路径：" + GetPath.class.getResource("/").getPath()); //  /C:/doctorwork_lsj/eclipse-workspace/ordinary_tst/target/classes/
		System.out.println("当前绝对路径：" + GetPath.class.getResource("").getPath()); // /C:/doctorwork_lsj/eclipse-workspace/ordinary_tst/target/classes/studiii/zlsj_test/
		System.out.println("文件路径：" + GetPath.class.getClassLoader().getResource("provider.xml").getPath()); // /C:/doctorwork_lsj/eclipse-workspace/ordinary_tst/target/classes/provider.xml
		System.out.println( System.getProperty("java.class.path")); 
		System.out.println(System.getProperty("user.dir"));
		System.out.println( "sdsf==="+ Thread.currentThread().getContextClassLoader().getResource("/").getPath());
	}
}
