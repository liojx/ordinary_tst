package studiii.zlsj_test.annotation.inheritedTest;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Author: liaosijun
 * Description:
 * DateTime: 2018年9月17日 下午2:41:44
 * Modified By:
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface InheAnnoTest {
	String value() default "ni da ye";
}
