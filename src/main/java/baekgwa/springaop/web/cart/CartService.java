package baekgwa.springaop.web.cart;

import baekgwa.springaop.web.cart.domain.Item;
import java.util.List;

public interface CartService {

    String saveNewItem(Item item);

    List<Item> loadAllCarts();

}
