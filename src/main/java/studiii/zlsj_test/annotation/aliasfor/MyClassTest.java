package studiii.zlsj_test.annotation.aliasfor;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.util.function.Consumer;

/**
 * @Description: TODO
 * @Author: liaosijun
 * @Date: 2019/5/5 17:19
 */
public class MyClassTest {

	@Test
	public void test1() throws NoSuchMethodException {
		Consumer<MyAliasAnnotation> logic = a -> {
			System.out.println(a.value());
			System.out.println(a.location());
			// Assert.assertTrue("", "这是值".equals(a.value()));
			Assert.assertTrue("", a.value().equals(a.location()));
		};

		MyAliasAnnotation aliasAnnotation = AnnotationUtils.findAnnotation(MyClass.class.getMethod("one"), MyAliasAnnotation.class);
		System.out.println("a-type" + aliasAnnotation.annotationType());
		logic.accept(aliasAnnotation);

		MyAliasAnnotation aliasAnnotation2 = AnnotationUtils.findAnnotation(MyClass.class.getMethod("one2"), MyAliasAnnotation.class);
		logic.accept(aliasAnnotation2);


		Class<MyAliasAnnotation> cc = MyAliasAnnotation.class;
		Annotation[]  annos = cc.getAnnotations();
		for(Annotation anno : annos) {
			System.out.println(anno.toString());
		}

	}
}