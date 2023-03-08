package market.eshop.domain.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
public class ModifyOrderCountForm {
    private Long itemId;
    @Min(1)
    private int orderCount;
    private String status;
}
