package baekgwa.springaop.global.aop.pointcuts;

import org.aspectj.lang.annotation.Pointcut;

public class WebPointcut {
    @Pointcut("execution(* baekgwa.springaop.web..*(..))")
    public void allMethod() {}

    @Pointcut("execution(* baekgwa.springaop.web..*Service*.*(..))")
    public void transactionOnlyService() {}
}
