package market.eshop.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {
    private List<OrderItemDto> orderItemList;
    private int totalAmount;

    public OrderDto(List<OrderItemDto> orderItemList) {
        this.orderItemList = orderItemList;
        this.totalAmount = orderItemList.stream()
                .mapToInt(orderItem -> orderItem.getTotalAmount())
                .sum();
    }
}
