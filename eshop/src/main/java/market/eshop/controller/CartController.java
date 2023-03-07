package market.eshop.controller;

import lombok.RequiredArgsConstructor;
import market.eshop.domain.Item;
import market.eshop.domain.Member;
import market.eshop.domain.dto.CartLineDto;
import market.eshop.domain.form.AddCartForm;
import market.eshop.service.CartService;
import market.eshop.web.session.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    /** 장바구니 리스트 */
    @GetMapping("/cart")
    public String getCartList(
            @SessionAttribute(
                    name = SessionConst.LOGIN_MEMBER,
                    required = false) Member memberId,
            Model model) {
        if(memberId == null)
            throw new IllegalStateException("로그인 후 이용 가능합니다.");

        List<CartLineDto> cartList = cartService.getCartPage(memberId);

        model.addAttribute("cartList", cartList);

        return "cart";
    }

    /** 장바구니 추가 */
    @PostMapping("/addCart")
    public String addCartLine(@ModelAttribute @Valid AddCartForm form,
                              @SessionAttribute(name = SessionConst.LOGIN_MEMBER,
                                      required = false) Member member) {
        //회원 아이디 유효성 검사
        if(member.getId() == null)
            throw new IllegalStateException("로그인 후 이용 가능합니다.");
        
        //장바구니 추가
        cartService.addCart(member.getId(), form);

        return "redirect:/cart";
    }

}
