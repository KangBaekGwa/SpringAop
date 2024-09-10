package baekgwa.springaop.global.aop.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

public class AspectV3 {

    @Aspect
    @Slf4j
    @Order(1)
    public static class DoLog {
        @Around("baekgwa.springaop.global.aop.pointcuts.WebPointcut.allMethod()")
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[START] : {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            log.info("[E N D] : {}", joinPoint.getSignature());

            return result;
        }
    }

    @Aspect
    @Slf4j
    @Order(2)
    public static class Transaction {

        @Around("baekgwa.springaop.global.aop.pointcuts.WebPointcut.allMethod() &&"
                + "baekgwa.springaop.global.aop.pointcuts.WebPointcut.transactionOnlyService()")
        public Object transaction(ProceedingJoinPoint joinPoint) throws Throwable {
            try {
                log.info("[Transaction Start] : {}", joinPoint.getSignature()); //@Before()
                Object result = joinPoint.proceed(); //JoinPoint
                log.info("[Transaction Commit] : {}", joinPoint.getSignature()); //@AfterReturning()
                return result;
            } catch (IllegalStateException e) {
                log.info("[Transaction Rollback] : {}", joinPoint.getSignature()); //@AfterThrowing()
                throw e;
            } finally {
                log.info("[Transaction Resource Clear] : {}", joinPoint.getSignature()); //@After()
            }
        }
    }
}
