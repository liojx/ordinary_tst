package studiii.zlsj_test.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Author: liaosijun
 * Description:
 * DateTime: 2018年9月14日 上午10:42:56
 * Modified By:
 */
@Retention(RetentionPolicy.SOURCE)
public @interface DocumentDemo {
	
	public abstract String[] value() default {};
	
	String[] value2() default {};
	
	String[] keyV() default {"A","B"};
	
	int a = 0;
	
	public static final int b = 0;
	
}
