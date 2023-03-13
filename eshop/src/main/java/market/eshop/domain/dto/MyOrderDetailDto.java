package market.eshop.domain.dto;

import lombok.*;
import market.eshop.domain.status.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class MyOrderDetailDto {
    private Long orderId;
    private List<MyOrderDetailItemDto> myOrderItemDtoList;
    private int totalAmount;
    private String orderStatus;
    private LocalDateTime orderDate;
}
