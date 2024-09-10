package baekgwa.springaop.global.aop.pointcuts;

import org.aspectj.lang.annotation.Pointcut;

public class ExecutionPointcut {

    /**
     * excution 지시자는 다음과 같은 조건으로 매칭을 시도 합니다.
     * execution("접근제한자 반환타입 패키지경로.메서드이름(파라미터...) throws 예외 class")
     * ex) execution("public String baekgwa.web.cart.CartController.saveNewItem(Integer))");
     * 풀이 : public 접근제한자로, String을 반환하며, ~~경로의 패키지중,
     * CartController 클래스(인터페이스)의 saveNewItem 메서드의 이름을 가지고,
     * 파라미터는 Integer 타입을 받는 조건을 만족하면 생성
     * --------------------------------------------------
     * 이중 접근 제한자와, 패키지경로, 예외는 생략이 가능 합니다.
     */

    //대표 예시.
    //해석 : 모든 접근제한자, 모든 반환타입, 패키징 경로는, ~~.web의 하위 모든 패키지에, 모든 메서드에
    //      모든 파라미터 허용.
    @Pointcut("execution(* baekgwa.springaop.web..*(..))")
    public void execution1(){}

    /**
     * 메서드 이름 매칭
     */
    @Pointcut("execution(* baekgwa.springaop.web..*save*(..))")
    public void methodNameMatch(){}

    /**
     * 패키지 이름 매칭
     */
    //..과 .의 차이점. 이건 매칭되는게 없을 예정.
    @Pointcut("execution(* baekgwa.springaop.web.*(..))")
    public void packageNameMatch1(){}

    //이건 매칭 될 예정.
    @Pointcut("execution(* baekgwa.springaop.web..*(..))")
    public void packageNameMatch2(){}

    /**
     * 타입 매칭
     */
    @Pointcut("execution(* baekgwa.springaop.web.cart.CartService.*(..))")
    public void typeMatching1(){}

    @Pointcut("execution(* baekgwa.springaop.web.cart.CartServiceImpl.*(..))")
    public void typeMatching2(){}

    /**
     * 파라미터 매칭
     */
    //있어도 되고, 없어도 되고, 많아도 상관없음.
    @Pointcut("execution(* baekgwa.springaop.web..*.*(..))")
    public void paramMatching1(){}

    //Item 하나만 무조건 있어야됨.
    @Pointcut("execution(* baekgwa.springaop.web..*.*(Item))")
    public void paramMatching2(){}

    //파라미터가 없어야 됨.
    @Pointcut("execution(* baekgwa.springaop.web..*.*())")
    public void paramMatching3(){}

    //시작이 Item으로 시작하고, 여러개도 허용
    @Pointcut("execution(* baekgwa.springaop.web..*.*(Item, ..))")
    public void paramMatching4(){}

    //어떤 것이든 무조건 하나만
    @Pointcut("execution(* baekgwa.springaop.web..*.*(*))")
    public void paramMatching5(){}
}
