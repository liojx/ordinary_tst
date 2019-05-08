package studiii.zlsj_test.annotation.aliasfor;

/**
 * @Description: TODO
 * @Author: liaosijun
 * @Date: 2019/5/5 17:20
 */
//编写测试类
public class MyClass {

	@MyAliasAnnotation(location = "这是值")
	public static void one(){
	}

	@MyAliasAnnotation(value = "这是值2")
	public static void one2(){
	}
}