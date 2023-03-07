package market.eshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import market.eshop.domain.embadded.CartLine;
import market.eshop.domain.exception.NotEnoughStockException;

import javax.persistence.*;

import java.util.HashMap;
import java.util.Map;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Cart {

    @Id @GeneratedValue
    private Long cartId;

    private Long memberId;

    @ElementCollection
    @CollectionTable(name ="cart_line")
    @MapKeyColumn(name = "map_key")
    private Map<Long, CartLine> cart = new HashMap<>();

    public Cart(Long id) {
        this.memberId = id;
    }

    public void addItemToCart(int stockQuantity, CartLine cartLine) {
        checkStockQuantity(stockQuantity, cartLine.getOrderCount());

        //아이템 아이디를 키로 지정
        Long key = cartLine.getItemId();

        //이미 존재하면 수량 추가
        if(cart.containsKey(key)) {
            CartLine existsLine = cart.get(key);
            int orderCount = existsLine.getOrderCount() + cartLine.getOrderCount();
            cart.replace(key, new CartLine(cartId, key, orderCount));
        }
        else    // 없으면 Map에 저장{
            cart.put(key, cartLine);
    }

    private void checkStockQuantity(int stockQuantity, Integer orderCount) {
        //재고 수량이 주문 수량보다 적다면
        if(stockQuantity < orderCount) {
            throw new NotEnoughStockException("재고 수량이 부족합니다.");
        }
    }
}
