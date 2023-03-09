package market.eshop.repository;

import market.eshop.domain.Order;
import market.eshop.repository.custom.MyOrderRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyOrderRepository extends JpaRepository<Order, Long>, MyOrderRepositoryCustom {

}
