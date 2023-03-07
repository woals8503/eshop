package market.eshop.dao;

import market.eshop.domain.Member;
import market.eshop.domain.dto.CartLineDto;

import java.util.List;

public interface CartDao {
    List<CartLineDto> getCartList(Member member);
}
