package market.eshop.domain.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSearchForm {
    private String name;
    private Long categoryId;
}
