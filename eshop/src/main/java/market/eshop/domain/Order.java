package market.eshop.domain;

import lombok.*;
import market.eshop.domain.base.BaseEntity;
import market.eshop.domain.status.OrderStatus;
import org.springframework.data.util.Lazy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    private int totalAmount;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @Builder
    public Order(Member member, List<OrderItem> orderItems, Delivery delivery) {
        this.member = member;
        this.delivery = delivery;
        this.setOrderItemList(orderItems);
        this.status = OrderStatus.ORDER;
    }

    private void setOrderItemList(List<OrderItem> orderItems) {
        //java.util.ConcurrentModificationException 오류 발생
        for (OrderItem orderItem : orderItems) {
            this.orderItems.add(orderItem);
        }
        this.calculateTotalAmount();
    }

    /** 계산 총액 */
    private void calculateTotalAmount() {
        this.totalAmount = this.orderItems.stream()
                .mapToInt(orderItem -> orderItem.getOrderItemAmount())
                .sum();
    }
}
