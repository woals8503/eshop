package market.eshop.controller;

import jdk.jfr.ContentType;
import lombok.RequiredArgsConstructor;
import market.eshop.domain.Member;
import market.eshop.domain.Order;
import market.eshop.domain.dto.OrderDto;
import market.eshop.domain.form.OrderRequestForm;
import market.eshop.repository.OrderRepository;
import market.eshop.service.OrderService;
import market.eshop.web.session.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @GetMapping("/getOrder")
    private String getOrderPage(@RequestParam(name = "itemId") List<Long> orderItemIdList,
                               @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,
                               Model model) {
        //아이디 리스트로 변환
//        List<Long> orderItemIdList = form.getOrderLineList()
//                .stream()
//                .map(o -> o.getItemId())
//                .collect(Collectors.toList());

        //회원 아이디, 주문 아이템 번호 를 넘김
        OrderDto result = orderRepository.getOrderInfoInCart(member.getId(), orderItemIdList);

        model.addAttribute("orderList", result);
        return "checkout";
    }

    @GetMapping("/order")
    public String orderPage(@ModelAttribute OrderRequestForm form) {
        return "myorder";
    }

    @PostMapping("/order")
    public String order(@ModelAttribute OrderRequestForm form,
                        @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member) {
        orderService.order(member.getId(), form);

        return "redirect:/catalog";
    }

}
