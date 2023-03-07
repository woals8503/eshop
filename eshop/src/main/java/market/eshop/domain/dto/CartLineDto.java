package market.eshop.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CartLineDto {
    private Long itemId;
    private String imagePath;
    private String name;
    private int price;
    private int orderCount;
    private int stockQuantity;

    @QueryProjection
    public CartLineDto(Long itemId, String imagePath, String itemName, int price, int orderCount, int stockQuantity) {
        this.itemId = itemId;
        this.imagePath = imagePath;
        this.name = itemName;
        this.price = price;
        this.orderCount = orderCount;
        this.stockQuantity = stockQuantity;
    }
}
