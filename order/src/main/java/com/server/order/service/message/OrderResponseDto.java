package com.server.order.service.message;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderResponseDto {
    private boolean status;
    private Long orderId;

    @Builder
    public OrderResponseDto(boolean status, Long orderId) {
        this.status = status;
        this.orderId = orderId;
    }
}
