package market.eshop.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import market.eshop.dao.CartDao;
import market.eshop.domain.Member;
import market.eshop.domain.dto.CartLineDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static market.eshop.domain.QCart.*;
import static market.eshop.domain.QItem.*;
import static market.eshop.domain.embadded.QCartLine.*;

@Repository
public class CartRepositoryImpl implements CartDao {
    private EntityManager em;
    private JPAQueryFactory queryFactory;

    public CartRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<CartLineDto> getCartList(Member member) {
        // 필드값 일치하지 않는 오류 발생 InvalidPathException
        // QuerySyntaxException: Invalid path: 'cartLine.cartId
        // 두 엔티티가 무슨 관계인지 모르겠다는 오류
        // 값타입은 엔티티가 아님
        List<CartLineDto> cartLineDtoList = em
                .createQuery("select " +
                        "new market.eshop.domain.dto.CartLineDto(" +
                        "i.id, " +
                        "i.imagePath, " +
                        "i.name, " +
                        "i.price, " +
                        "cl.orderCount, " +
                        "i.stockQuantity)" +
                        " from Cart c" +
                        " join c.cart cl" +
                        " on c.cartId = cl.cartId" +
                        " join Item i" +
                        " on cl.itemId = i.id" +
                        " where c.memberId = :memberId", CartLineDto.class)
                .setParameter("memberId", member.getId())
                .getResultList();

        return cartLineDtoList;
    }
}
