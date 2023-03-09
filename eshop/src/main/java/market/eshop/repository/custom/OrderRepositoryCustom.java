package market.eshop.repository.custom;

import com.querydsl.jpa.impl.JPAQuery;
import market.eshop.domain.Order;
import market.eshop.domain.dto.OrderDto;
import market.eshop.domain.dto.OrderItemDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepositoryCustom {
    OrderDto getOrderInfoInCart(Long id, List<Long> orderItemIdList);

}
