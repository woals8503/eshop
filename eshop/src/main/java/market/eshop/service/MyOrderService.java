package market.eshop.service;

import lombok.RequiredArgsConstructor;
import market.eshop.domain.Item;
import market.eshop.domain.Member;
import market.eshop.domain.Order;
import market.eshop.domain.dto.MyOrderDetailDto;
import market.eshop.domain.dto.MyOrderDetailItemDto;
import market.eshop.domain.dto.MyOrderDto;
import market.eshop.domain.dto.MyOrderSummaryDto;
import market.eshop.repository.MyOrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MyOrderService {

    private final MyOrderRepository myOrderRepository;

    public MyOrderSummaryDto getOrderList(Member member, Pageable pageable) {
        //주문 리스트 조회
        Page<Order> myOrderList = myOrderRepository.findByOrderList(member.getId(), pageable);


        // 이미지와 이름을 가져오는 시점에서 1+N문제 발생
        List<MyOrderDto> contents = myOrderList.stream()
                .map(o -> MyOrderDto.builder()
                        .orderId(o.getId())
                        .orderDate(o.getCreatedDate())
                        .representativeImagePath(o.getOrderItems().get(0).getItem().getImagePath())
                        .representativeItemName(o.getOrderItems().get(0).getItem().getName())
                        .totalAmount(o.getTotalAmount())
                        .orderStatus(o.getStatus().getStatus())
                        .orderCount(o.getOrderItems().size())
                        .build())
                .collect(Collectors.toList());

        int total = contents.size();

        return new MyOrderSummaryDto(contents, total);
    }

    public MyOrderDetailDto getMyOrderDetail(Member member, Long orderId) {
        //주문 정보
        Order order = myOrderRepository.findByOrderDetailList(orderId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문번호입니다."));
        System.out.println(order.getStatus());

        //주문 상품 DTO 변환 작업
        List<MyOrderDetailItemDto> myOrderDetailItemDtoList = order.getOrderItems().stream()
                .map(oi -> {
                    Item item = oi.getItem();
                    return new MyOrderDetailItemDto(item.getId(), item.getName(), item.getImagePath(), item.getPrice(), oi.getCount());
                })
                .collect(Collectors.toList());

        MyOrderDetailDto result = MyOrderDetailDto.builder()
                .orderId(orderId)
                .myOrderItemDtoList(myOrderDetailItemDtoList)
                .totalAmount(order.getTotalAmount())
                .orderStatus(order.getStatus().getStatus())
                .orderDate(order.getCreatedDate())
                .build();

        return result;

    }
}
