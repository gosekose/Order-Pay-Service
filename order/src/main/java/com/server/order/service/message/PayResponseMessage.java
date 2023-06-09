package com.server.order.service.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PayResponseMessage {
    private boolean status;
    private Long memberId;
    private Long productId;
    private Long orderId;
}
