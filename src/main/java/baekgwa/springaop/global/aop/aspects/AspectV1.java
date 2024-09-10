package baekgwa.springaop.global.aop.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Slf4j
public class AspectV1 {

    //case1) 직접 pointcut 작성하여 적용
//    @Around("execution(* baekgwa.springaop.web..*(..))")
    //case2) 외부에서 pointcut 만들어서 주입. 재활용 가능한 장점이 있음. (모듈화)
    @Around("baekgwa.springaop.global.aop.pointcuts.WebPointcut.allMethod()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[START] : {}", joinPoint.getSignature());
        Object result = joinPoint.proceed();
        log.info("[E N D] : {}", joinPoint.getSignature());

        return result;
    }
}
