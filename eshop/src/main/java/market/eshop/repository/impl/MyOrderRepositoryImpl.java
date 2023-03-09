package market.eshop.repository.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import market.eshop.domain.Order;
import market.eshop.domain.QDelivery;
import market.eshop.domain.QMember;
import market.eshop.domain.QOrder;
import market.eshop.repository.custom.MyOrderRepositoryCustom;
import market.eshop.repository.custom.OrderRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static market.eshop.domain.QDelivery.*;
import static market.eshop.domain.QMember.*;
import static market.eshop.domain.QOrder.*;

@Repository
public class MyOrderRepositoryImpl implements MyOrderRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MyOrderRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Order> findByOrderList(Long memberId, Pageable pageable) {
        QueryResults<Order> orderByOrderedId = queryFactory
                .select(order)
                .from(order)
                .join(order.member, member).fetchJoin()
                .join(order.delivery, delivery).fetchJoin()
                .where(order.member.id.eq(memberId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(order.id.desc())
                .fetchResults();

        List<Order> contents = orderByOrderedId.getResults();
        long total = orderByOrderedId.getTotal();

        return new PageImpl<>(contents, pageable, total);
    }
}
