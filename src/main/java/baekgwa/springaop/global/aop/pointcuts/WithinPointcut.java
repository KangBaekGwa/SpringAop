package baekgwa.springaop.global.aop.pointcuts;

import org.aspectj.lang.annotation.Pointcut;

public class WithinPointcut {

    @Pointcut("within(baekgwa.springaop.web.cart.CartServiceImpl)")
    public void withinMatch1(){}

    @Pointcut("within(baekgwa.springaop.web..*Service*)")
    public void withinMatch2(){}
}
