package market.eshop.domain.form;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineForm {
    private Long itemId;
    private int orderCount;
}
