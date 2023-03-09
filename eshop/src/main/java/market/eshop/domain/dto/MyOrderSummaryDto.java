package market.eshop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class MyOrderSummaryDto {
    private List<MyOrderDto> myOrderList;
    private int total;
}
