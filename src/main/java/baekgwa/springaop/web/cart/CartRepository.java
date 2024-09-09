package baekgwa.springaop.web.cart;

import baekgwa.springaop.web.cart.domain.Item;
import java.util.List;

public interface CartRepository {

    String save(Item item);
    List<Item> findAll();
}
