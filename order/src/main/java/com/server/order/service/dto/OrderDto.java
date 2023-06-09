package com.server.order.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderDto {
    private Long productId;
    private Long memberId;
    private Long amount;

    @Builder
    public OrderDto(Long productId, Long memberId, Long amount) {
        this.productId = productId;
        this.memberId = memberId;
        this.amount = amount;
    }
}
