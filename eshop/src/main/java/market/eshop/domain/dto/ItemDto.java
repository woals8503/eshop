package market.eshop.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import org.springframework.data.jpa.repository.Query;

@Getter
@Setter
@NoArgsConstructor
public class ItemDto {
    private Long id;
    private String name;
    private String imagePath;
    private int price;
    private int stockQuantity;
    private Long categoryId;

    @QueryProjection
    public ItemDto(Long id, String name, String imagePath, int price, int stockQuantity, Long categoryId) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categoryId = categoryId;
    }
}
