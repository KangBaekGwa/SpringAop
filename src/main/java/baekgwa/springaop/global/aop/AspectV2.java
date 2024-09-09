package baekgwa.springaop.global.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Slf4j
public class AspectV2 {

    @Around("baekgwa.springaop.global.aop.pointcuts.WebPointcut.allMethod()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[START] : {}", joinPoint.getSignature());
        Object result = joinPoint.proceed();
        log.info("[E N D] : {}", joinPoint.getSignature());

        return result;
    }

    @Around("baekgwa.springaop.global.aop.pointcuts.WebPointcut.allMethod() &&"
            + "baekgwa.springaop.global.aop.pointcuts.WebPointcut.transactionOnlyService()")
    public Object transaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            log.info("[Transaction Start] : {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            log.info("[Transaction Commit] : {}", joinPoint.getSignature());
            return result;
        } catch (IllegalStateException e) {
            log.info("[Transaction Rollback] : {}", joinPoint.getSignature());
            throw e;
        } finally {
            log.info("[Transaction Resource Clear] : {}", joinPoint.getSignature());
        }
    }
}
