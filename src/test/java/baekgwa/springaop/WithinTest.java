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
public class WithinTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method saveNewItemMethod = null;

    private final CartServiceImpl cartServiceImpl;

    @Autowired
    public WithinTest(CartServiceImpl cartServiceImpl) {
        this.cartServiceImpl = cartServiceImpl;
    }

    @BeforeEach
    void init() throws NoSuchMethodException {
        saveNewItemMethod = cartServiceImpl.getClass().getMethod("saveNewItem", Item.class);
    }

    @Test
    @DisplayName("within() test1")
    void withinMatchTest1() {
        pointcut.setExpression("within(baekgwa.springaop.web.cart.CartServiceImpl)");
        assertThat(pointcut.matches(saveNewItemMethod, CartServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("within() test2")
    void withinMatchTest2() {
        pointcut.setExpression("within(baekgwa.springaop.web..*Service*)");
        assertThat(pointcut.matches(saveNewItemMethod, CartServiceImpl.class)).isTrue();
    }
}
