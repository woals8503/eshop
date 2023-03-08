package market.eshop.repository;

import com.querydsl.jpa.impl.JPAQuery;
import market.eshop.domain.Order;
import market.eshop.domain.dto.OrderItemDto;
import market.eshop.repository.custom.OrderRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {

}
