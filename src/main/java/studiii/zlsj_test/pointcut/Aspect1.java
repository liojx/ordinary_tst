package studiii.zlsj_test.pointcut;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Aspect1 {

    @Before(value = "studiii.zlsj_test.pointcut.PointCuts.aopDemo()") //
    public void before(JoinPoint joinPoint) {
        System.out.println("[Aspect1] 在建言之前");
    }

    @Around(value = "studiii.zlsj_test.pointcut.PointCuts.aopDemo()") //
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("[Aspect1] around advise 1");
        pjp.proceed();
        System.out.println("[Aspect1] around advise 2");
    }

    @AfterReturning(value = "studiii.zlsj_test.pointcut.PointCuts.aopDemo()")
    public void afterReturning(JoinPoint joinPoint) {
        System.out.println("[Aspect1] afterReturning advise");
    }

    @AfterThrowing(value = "studiii.zlsj_test.pointcut.PointCuts.aopDemo()")
    public void afterThrowing(JoinPoint joinPoint) {
        System.out.println("[Aspect1] afterThrowing advise");
    }

    @After(value = "studiii.zlsj_test.pointcut.PointCuts.aopDemo()")
    public void after(JoinPoint joinPoint) {
        System.out.println("[Aspect1] after advise");
    }
}
