package studiii.zlsj_test.annotation.inheritedTest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: liaosijun
 * Description:
 * DateTime: 2018年9月17日 下午4:33:27
 * Modified By:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodTestAnno {
	String value() default "methods";
}
