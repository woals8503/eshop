package market.eshop.domain;

import lombok.*;
import market.eshop.domain.base.BaseEntity;
import market.eshop.domain.exception.NotEnoughStockException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String imagePath;
    private String name;
    private int price;
    private int stockQuantity;

    private Long categoryId;

    @Builder
    public Item(String imagePath, String name, int price, int stockQuantity, Long categoryId) {
        this.imagePath = imagePath;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categoryId = categoryId;
    }

    public void removeStockQuantity(int orderQuantity) {
        int restStockQuantity = this.stockQuantity - orderQuantity;

        if(restStockQuantity < 0)
            throw new NotEnoughStockException();

        this.stockQuantity = restStockQuantity;
    }
}
