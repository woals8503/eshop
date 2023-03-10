package market.eshop.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class MyOrderDto {
    private Long orderId;
    private LocalDateTime orderDate;
    private String representativeImagePath; // 대표 사진
    private String representativeItemName;  // 대표 아이템 이름
    private int totalAmount;
    private String orderStatus;
    private int orderCount;

    @Builder
    public MyOrderDto(Long orderId, LocalDateTime orderDate, String representativeImagePath, String representativeItemName, int totalAmount, String orderStatus, int orderCount) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.representativeImagePath = representativeImagePath;
        this.representativeItemName = representativeItemName;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.orderCount = orderCount;
    }
}
