package studiii.zlsj_test.annotation.aliasfor;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//定义注解
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyAliasAnnotation {

	@AliasFor(value = "location")
	String value() default "";

	@AliasFor(value = "value")
	String location() default "";

}