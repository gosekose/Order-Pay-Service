package com.server.order.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderForPayDto {
    private Long memberId;
    private Long productId;
    private Long orderId;
    private Long totalPrice;

    @Builder
    public OrderForPayDto(Long memberId, Long productId, Long orderId, Long totalPrice) {
        this.memberId = memberId;
        this.productId = productId;
        this.orderId = orderId;
        this.totalPrice = totalPrice;
    }
}
