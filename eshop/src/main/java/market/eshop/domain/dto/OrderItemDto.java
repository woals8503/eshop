package market.eshop.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemDto {
    private Long itemId;
    private String name;
    private String imagePath;
    private int price;
    private int orderCount;
    private int totalAmount;

    @QueryProjection
    public OrderItemDto(Long itemId, String name, String imagePath, int price, int orderCount) {
        this.itemId = itemId;
        this.name = name;
        this.imagePath = imagePath;
        this.price = price;
        this.orderCount = orderCount;
        this.totalAmount = price * orderCount;
    }
}
