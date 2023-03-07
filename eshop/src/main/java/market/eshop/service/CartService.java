package market.eshop.service;

import lombok.RequiredArgsConstructor;
import market.eshop.dao.CartDao;
import market.eshop.domain.Cart;
import market.eshop.domain.Member;
import market.eshop.domain.dto.CartLineDto;
import market.eshop.domain.embadded.CartLine;
import market.eshop.domain.form.AddCartForm;
import market.eshop.repository.CartRepository;
import market.eshop.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartDao cartDao;
    private final ItemRepository itemRepository;

    public List<CartLineDto> getCartPage(Member memberId) {
        return cartDao.getCartList(memberId);
    }

    @Transactional
    public void addCart(Long memberId, AddCartForm form) {
        //회원의 장바구니 정보 조회
        Cart cart = cartRepository.findByMemberId(memberId);

        //장바구니 라인 생성
        CartLine cartLine = new CartLine(cart.getCartId(), form.getItemId(), form.getOrderCount());

        //아이템 정보를 찾아서 재고 수량을 가져옴
        int stockQuantity = itemRepository.findById(form.getItemId())
                .get()
                .getStockQuantity();
        //장바구니 추가
        cart.addItemToCart(stockQuantity, cartLine);
    }

    public Long createCart(Long id) {
        return cartRepository.save(new Cart(id)).getCartId();
    }
}
