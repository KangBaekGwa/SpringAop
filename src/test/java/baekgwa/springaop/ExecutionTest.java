package baekgwa.springaop;

import static org.assertj.core.api.Assertions.assertThat;

import baekgwa.springaop.web.cart.CartServiceImpl;
import baekgwa.springaop.web.cart.domain.Item;
import java.lang.reflect.Method;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExecutionTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method saveNewItemMethod = null;

    private final CartServiceImpl cartServiceImpl;

    @Autowired
    public ExecutionTest(CartServiceImpl cartServiceImpl) {
        this.cartServiceImpl = cartServiceImpl;
    }

    @BeforeEach
    void init() throws NoSuchMethodException {
        saveNewItemMethod = cartServiceImpl.getClass().getMethod("saveNewItem", Item.class);
    }

    //대표 예시.
    //해석 : 모든 접근제한자, 모든 반환타입, 패키징 경로는, ~~.web의 하위 모든 패키지에, 모든 메서드에
    //      모든 파라미터 허용.
    @Test
    @DisplayName("execution() 사용해보기")
    void executionTest() {
        pointcut.setExpression("execution(* baekgwa.springaop.web..*(..))");
        assertThat(pointcut.matches(saveNewItemMethod, CartServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("메서드 이름으로 매칭")
    public void methodNameMatchTest(){
        pointcut.setExpression("execution(* baekgwa.springaop.web..*save*(..))");
        assertThat(pointcut.matches(saveNewItemMethod, CartServiceImpl.class)).isTrue();
    }

    /**
     * 패키지 이름 매칭
     */
    @Test
    @DisplayName("패키지 매칭. 실패 Case")
    public void packageNameMatchTest1(){
        pointcut.setExpression("execution(* baekgwa.springaop.web.*(..))");
        assertThat(pointcut.matches(saveNewItemMethod, CartServiceImpl.class)).isFalse();
    }

    @Test
    @DisplayName("패키징 매치. 성공 Case")
    public void packageNameMatchTest2(){
        pointcut.setExpression("execution(* baekgwa.springaop.web..*(..))");
        assertThat(pointcut.matches(saveNewItemMethod, CartServiceImpl.class)).isTrue();
    }

    /**
     * 타입 매칭
     */
    @Test
    @DisplayName("타입 매칭 / 부모 <-> 자식")
    public void typeMatchingTest1(){
        pointcut.setExpression("execution(* baekgwa.springaop.web.cart.CartService.*(..))");
        assertThat(pointcut.matches(saveNewItemMethod, CartServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("타입 매칭 / 자식 <-> 자식")
    public void typeMatchingTest2(){
        pointcut.setExpression("execution(* baekgwa.springaop.web.cart.CartServiceImpl.*(..))");
        assertThat(pointcut.matches(saveNewItemMethod, CartServiceImpl.class)).isTrue();
    }


    /**
     * 파라미터 매칭
     */
    //
    @Test
    @DisplayName("파라미터 매칭 / 있어도 되고, 없어도 되고, 많아도 상관없음.")
    public void paramMatchingTest1(){
        pointcut.setExpression("execution(* baekgwa.springaop.web..*.*(..))");
        assertThat(pointcut.matches(saveNewItemMethod, CartServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("파라미터 매칭 / Item 하나만 무조건 있어야됨.")
    public void paramMatchingTest2() throws NoSuchMethodException {
        pointcut.setExpression("execution(* baekgwa.springaop.web..*.*(baekgwa.springaop.web.cart.domain.Item))");
        assertThat(pointcut.matches(saveNewItemMethod, CartServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("파라미터 매칭 / 파라미터가 없어야 됨.")
    public void paramMatchingTest3(){
        pointcut.setExpression("execution(* baekgwa.springaop.web..*.*())");
        assertThat(pointcut.matches(saveNewItemMethod, CartServiceImpl.class)).isFalse();
    }

    @Test
    @DisplayName("파라미터 매칭 / 시작이 Item으로 시작하고, 여러개도 허용")
    public void paramMatchingTest4(){
        pointcut.setExpression("execution(* baekgwa.springaop.web..*.*(baekgwa.springaop.web.cart.domain.Item, ..))");
        assertThat(pointcut.matches(saveNewItemMethod, CartServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("파라미터 매칭 / 어떤 것이든 무조건 하나만")
    public void paramMatchingTest5(){
        pointcut.setExpression("execution(* baekgwa.springaop.web..*.*(*))");
        assertThat(pointcut.matches(saveNewItemMethod, CartServiceImpl.class)).isTrue();
    }
}
