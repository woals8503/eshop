package market.eshop.repository.custom;

import market.eshop.domain.Order;
import market.eshop.domain.dto.MyOrderDetailDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MyOrderRepositoryCustom {
    Page<Order> findByOrderList(Long id, Pageable pageable);

    Optional<Order> findByOrderDetailList(Long orderId);
}
