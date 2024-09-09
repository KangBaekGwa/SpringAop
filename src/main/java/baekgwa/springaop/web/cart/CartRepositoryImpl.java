package baekgwa.springaop.web.cart;

import baekgwa.springaop.web.cart.domain.Item;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepositoryImpl implements CartRepository {

    List<Item> carts = new CopyOnWriteArrayList<>();

    @Override
    public String save(Item item) {
        if(Boolean.TRUE.equals(carts.contains(item))){
            throw new IllegalStateException("이미 존재하는 item 입니다");
        }
        carts.add(item);

        return "ok";
    }

    @Override
    public List<Item> findAll() {
        if(carts.isEmpty()){
            throw new IllegalStateException("카트에 Item 이 존재하지 않습니다.");
        }

        return carts;
    }
}
