package market.eshop.domain.form;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AddItemForm {

    private String name;
    private String imagePath;
    private int price;
    private int stockQuantity;
    private Long categoryId;
}
