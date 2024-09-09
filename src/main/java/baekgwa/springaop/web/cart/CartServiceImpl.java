package baekgwa.springaop.web.cart;

import baekgwa.springaop.web.cart.domain.Item;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;

    @Override
    public String saveNewItem(Item item) {
        return cartRepository.save(item);
    }

    @Override
    public List<Item> loadAllCarts() {
        return cartRepository.findAll();
    }
}
