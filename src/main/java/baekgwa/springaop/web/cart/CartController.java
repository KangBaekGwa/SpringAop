package baekgwa.springaop.web.cart;

import baekgwa.springaop.web.cart.domain.Item;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping("/items")
    public String saveNewItem(
            @RequestBody(required = true) Item item){
        return cartService.saveNewItem(item);
    }

    @GetMapping("/items")
    public List<Item> loadAllCart(){
        return cartService.loadAllCarts();
    }
}
