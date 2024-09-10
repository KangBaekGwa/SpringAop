package baekgwa.springaop.global.aop.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class AspectV5 {

    @Before("baekgwa.springaop.global.aop.pointcuts.ExecutionPointcut.execution1()")
    public void execution1(JoinPoint joinPoint){
        log.info("[테스트] : {}", joinPoint.getSignature());
    }
}