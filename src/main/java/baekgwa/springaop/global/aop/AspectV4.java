package baekgwa.springaop.global.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;

public class AspectV4 {

    @Aspect
    @Slf4j
    @Order(2)
    public static class Transaction {

        @Before("baekgwa.springaop.global.aop.pointcuts.WebPointcut.allMethod() &&"
                + "baekgwa.springaop.global.aop.pointcuts.WebPointcut.transactionOnlyService()")
        public void doTransactionBefore(JoinPoint joinPoint){
            log.info("[Transaction Start] : {}", joinPoint.getSignature());
        }

        @AfterReturning(value = "baekgwa.springaop.global.aop.pointcuts.WebPointcut.allMethod() &&"
                + "baekgwa.springaop.global.aop.pointcuts.WebPointcut.transactionOnlyService()", returning = "result")
        public void doTransactionAfterReturning(JoinPoint joinPoint, Object result){
            log.info("[Transaction Commit] : {}", joinPoint.getSignature()); //@AfterReturning()
            log.info("[AfterReturning Result] = {}", result);
        }

        @AfterThrowing(value = "baekgwa.springaop.global.aop.pointcuts.WebPointcut.allMethod() &&"
                + "baekgwa.springaop.global.aop.pointcuts.WebPointcut.transactionOnlyService()", throwing = "ex")
        public void doTransactionAfterThrowing(JoinPoint joinPoint, Exception ex){
            log.info("[Transaction Rollback] : {}", joinPoint.getSignature());
            log.info("[AfterThrowing Result] = {}", ex.getMessage());
        }

        @After("baekgwa.springaop.global.aop.pointcuts.WebPointcut.allMethod() &&"
                + "baekgwa.springaop.global.aop.pointcuts.WebPointcut.transactionOnlyService()")
        public void doTransactionAfter(JoinPoint joinPoint) {
            log.info("[Transaction Resource Clear] : {}", joinPoint.getSignature());
        }
    }
}
