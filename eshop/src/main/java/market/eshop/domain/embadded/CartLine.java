package market.eshop.domain.embadded;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@NoArgsConstructor
@Getter
public class CartLine {
    private Long cartId;
    private Long itemId;
    private Integer orderCount;

    public CartLine(Long cartId, Long itemId, Integer orderCount) {
        this.cartId = cartId;
        this.itemId = itemId;
        this.orderCount = orderCount;
    }
}
