package market.eshop.repository;

import market.eshop.domain.Cart;
import market.eshop.domain.Member;
import market.eshop.domain.dto.CartLineDto;
import market.eshop.repository.custom.CartRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long>, CartRepositoryCustom {

    Cart findByMemberId(Long memberId);

}
