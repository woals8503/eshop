package market.eshop.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import market.eshop.domain.QCart;
import market.eshop.domain.dto.OrderDto;
import market.eshop.domain.dto.OrderItemDto;
import market.eshop.repository.custom.OrderRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static market.eshop.domain.QCart.*;
import static market.eshop.domain.QItem.*;
import static market.eshop.domain.embadded.QCartLine.*;

@Repository
public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public OrderRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public OrderDto getOrderInfoInCart(Long id, List<Long> orderItemIdList) {
        List<OrderItemDto> resultList = em.createQuery(
                "select new market.eshop.domain.dto." +
                        "OrderItemDto(i.id, i.name, i.imagePath, i.price, cl.orderCount) " +
                        "from Cart c " +
                        "join c.cart cl " +
                        "on c.cartId = cl.cartId " +
                        "join Member m " +
                        "on c.memberId = m.id " +
                        "join Item i " +
                        "on cl.itemId = i.id " +
                        "where m.id = :memberId " +
                        "and cl.itemId in :cartItemList", OrderItemDto.class)
                        .setParameter("memberId", id)
                        .setParameter("cartItemList", orderItemIdList)
                        .getResultList();

        return new OrderDto(resultList);
    }
}
