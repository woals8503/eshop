package market.eshop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;
    private int orderPrice;
    private int count;
    private int orderItemAmount;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public OrderItem(int count, Item item) {
        this.count = count;
        this.item = item;
        this.calculateOrderItemAmount();
    }

    private void calculateOrderItemAmount() {
        this.orderItemAmount = this.item.getPrice() * count;
    }

    public void removeStockQuantity() {
        this.item.removeStockQuantity(count);
    }
}
