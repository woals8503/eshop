package market.eshop.domain.form;

import lombok.*;

import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AddCartForm {

    private Long itemId;

    @Min(1) //최소 1개는 주문해야함
    private Integer orderCount;
}
