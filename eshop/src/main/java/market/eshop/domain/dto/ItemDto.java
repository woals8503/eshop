package market.eshop.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import org.springframework.data.jpa.repository.Query;

@Getter
@Setter
@NoArgsConstructor
public class ItemDto {
    private String name;
    private String imagePath;
    private int price;
    private Long categoryId;

    @QueryProjection
    public ItemDto(String name, String imagePath, int price, Long categoryId) {
        this.name = name;
        this.imagePath = imagePath;
        this.price = price;
        this.categoryId = categoryId;
    }
}
