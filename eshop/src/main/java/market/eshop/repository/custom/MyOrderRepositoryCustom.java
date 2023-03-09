package market.eshop.repository.custom;

import market.eshop.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MyOrderRepositoryCustom {
    Page<Order> findByOrderList(Long id, Pageable pageable);
}
