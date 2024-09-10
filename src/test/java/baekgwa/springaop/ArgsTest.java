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
public class ArgsTest {

    Method saveNewItemMethod = null;

    private final CartServiceImpl cartServiceImpl;

    @Autowired
    public ArgsTest(CartServiceImpl cartServiceImpl) {
        this.cartServiceImpl = cartServiceImpl;
    }

    @BeforeEach
    void init() throws NoSuchMethodException {
        saveNewItemMethod = cartServiceImpl.getClass().getMethod("saveNewItem", Item.class);
    }

    private AspectJExpressionPointcut pointcut(String expression) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        return pointcut;
    }

    @Test
    @DisplayName("Args() test1")
    void argsMatchTest1() {

        assertThat(pointcut("args(String)")
                .matches(saveNewItemMethod, CartServiceImpl.class)).isFalse();

        assertThat(pointcut("args(baekgwa.springaop.web.cart.domain.Item)")
                .matches(saveNewItemMethod, CartServiceImpl.class)).isTrue();

        assertThat(pointcut("args(Object)")
                .matches(saveNewItemMethod, CartServiceImpl.class)).isTrue();

        assertThat(pointcut("args()")
                .matches(saveNewItemMethod, CartServiceImpl.class)).isFalse();

        assertThat(pointcut("args(..)")
                .matches(saveNewItemMethod, CartServiceImpl.class)).isTrue();

        assertThat(pointcut("args(*)")
                .matches(saveNewItemMethod, CartServiceImpl.class)).isTrue();

        assertThat(pointcut("args(baekgwa.springaop.web.cart.domain.Item, ..)")
                .matches(saveNewItemMethod, CartServiceImpl.class)).isTrue();
    }

    @Test
    void argsVsExecutionTest() {
        //args
        //부모타입을 허용. 실제 넘어온 파라미터 객체 인스턴스를 보고 판단.
        //Item 참조 - 성공
        assertThat(pointcut("args(baekgwa.springaop.web.cart.domain.Item)")
                .matches(saveNewItemMethod, CartServiceImpl.class)).isTrue();

        //Interface 참조 - 성공
        assertThat(pointcut("args(baekgwa.springaop.web.cart.CartService)")
                .matches(saveNewItemMethod, CartServiceImpl.class)).isTrue();

        //최상위 Object - 성공
        assertThat(pointcut("args(Object)")
                .matches(saveNewItemMethod, CartServiceImpl.class)).isTrue();


        //execution
        //정확하게 매칭되는 것만 매칭
        //Item 참조 - 성공
        assertThat(pointcut("execution(* *(baekgwa.springaop.web.cart.domain.Item))")
                .matches(saveNewItemMethod, CartServiceImpl.class)).isTrue();

        //Interface 참조 - 실패
        assertThat(pointcut("execution(* *(baekgwa.springaop.web.cart.CartService))")
                .matches(saveNewItemMethod, CartServiceImpl.class)).isFalse();

        //최상위 Object - 실패
        assertThat(pointcut("execution(* *(Object))")
                .matches(saveNewItemMethod, CartServiceImpl.class)).isFalse();
    }
}
