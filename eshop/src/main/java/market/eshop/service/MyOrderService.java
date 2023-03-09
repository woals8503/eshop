package market.eshop.service;

import lombok.RequiredArgsConstructor;
import market.eshop.domain.Member;
import market.eshop.domain.Order;
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
        System.out.println("///////////////////////////////////////////////");

        // 이미지와 이름을 가져오는 시점에서 1+N문제 발생
        List<MyOrderDto> contents = myOrderList.stream()
                .map(o -> MyOrderDto.builder()
                        .orderId(o.getId())
                        .orderDate(o.getCreatedDate())
                        .representativeImagePath(o.getOrderItems().get(0).getItem().getImagePath())
                        .representativeItemName(o.getOrderItems().get(0).getItem().getName())
                        .totalAmount(o.getTotalAmount())
                        .orderStatus(o.getStatus().getStatus())
                        .build())
                .collect(Collectors.toList());
        System.out.println("///////////////////////////////////////////////");
        int total = contents.size();

        return new MyOrderSummaryDto(contents, total);
    }
}
