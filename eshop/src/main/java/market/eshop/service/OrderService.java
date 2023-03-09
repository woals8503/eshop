package market.eshop.service;

import lombok.RequiredArgsConstructor;
import market.eshop.domain.*;
import market.eshop.domain.form.OrderRequestForm;
import market.eshop.repository.ItemRepository;
import market.eshop.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberService memberService;
    private final ItemRepository itemRepository;

    /**
     * 장바구니에서 주문
     *
     * @return
     */
    @Transactional
    public Order order(Long orderId, OrderRequestForm form) {
//        회원 조회
        Member member = memberService.findMember(orderId);

//        배송지 설정
        Delivery delivery = Delivery.builder()
                .address(member.getAddress())
                .build();
        
        //주문 아이템 리스트 생성
        List<OrderItem> orderItemList = form.getOrderLineList()
                .stream()
                .map(ol -> {
                    Item item = itemRepository.findById(ol.getItemId()).get();  // Item 조회
                    return new OrderItem(ol.getOrderCount(), item);             // 주문 아이템 생성
                })
                .collect(Collectors.toList());
        
        //구매 했으니 상품 재고 감소
        orderItemList.stream()
                .forEach(oi -> oi.removeStockQuantity());

        //주문 엔티티 생성
        Order order = Order.builder()
                .member(member)
                .delivery(delivery)
                .orderItems(orderItemList)
                .build();
        Order save = orderRepository.save(order);
        return save;
    }
}
