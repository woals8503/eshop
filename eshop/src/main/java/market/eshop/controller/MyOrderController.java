package market.eshop.controller;

import lombok.RequiredArgsConstructor;
import market.eshop.domain.Member;
import market.eshop.domain.dto.MyOrderDetailDto;
import market.eshop.domain.dto.MyOrderSummaryDto;
import market.eshop.service.MyOrderService;

import market.eshop.web.session.SessionConst;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;


@Controller
@RequiredArgsConstructor
public class MyOrderController {

    private final MyOrderService myOrderService;

    @GetMapping("/myOrder")
    public String getMyOrderListPage(@SessionAttribute(name = SessionConst.LOGIN_MEMBER) Member member,
                                         Pageable pageable, Model model)  {
        MyOrderSummaryDto orderList = myOrderService.getOrderList(member, pageable);
        orderList.getMyOrderList().get(0).getOrderId();
        model.addAttribute("orderList", orderList);
        return "myorder";
    }

    @GetMapping("/myOrderDetail")
    public String getMyOrderDetail(@RequestParam(value = "id") Long orderId,
                                   @SessionAttribute(name = SessionConst.LOGIN_MEMBER) Member member,
                                   Model model) {
        MyOrderDetailDto result = myOrderService.getMyOrderDetail(member, orderId);
        System.out.println(result.getTotalAmount());
        model.addAttribute("myOrderDetailList", result);
        return "myorderdetail";
    }
}
