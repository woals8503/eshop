package market.eshop.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MyOrderDetailItemDto {
    private Long itemId;
    private String name;
    private String imagePath;
    private int price;
    private int orderCount;
    private int totalAmount;

    public MyOrderDetailItemDto(Long itemId, String name, String imagePath, int price, int orderCount) {
        this.itemId = itemId;
        this.name = name;
        this.imagePath = imagePath;
        this.price = price;
        this.orderCount = orderCount;
        this.totalAmount = price * orderCount;
    }
}
