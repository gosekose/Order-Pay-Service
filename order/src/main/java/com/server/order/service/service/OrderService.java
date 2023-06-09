package com.server.order.service.service;

import com.server.order.domain.Member;
import com.server.order.domain.Orders;
import com.server.order.domain.Product;
import com.server.order.repository.OrderRepository;
import com.server.order.service.dto.OrderDto;
import com.server.order.service.dto.OrderForPayDto;
import com.server.order.service.message.OrderResponseDto;
import com.server.order.service.message.PayResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final MemberService memberService;
    private final ProductService productService;
    private final OrderRepository orderRepository;

    public OrderForPayDto orderWithPayStatusFalse(OrderDto dto) {
        Member member = memberService.findById(dto.getMemberId());
        Product product = productService.findById(dto.getProductId());

        // 먼저 제거 후 요청 -> 요청 성공하면 그대로 처리
        // 실패하면 복구 작업
        if (product.isEnoughAmount(dto.getAmount())) {
            product.minusStock(dto.getAmount());
            Orders orders = orderRepository.save(Orders.builder().member(member)
                    .amount(dto.getAmount())
                    .product(product).build());

            return OrderForPayDto
                    .builder().memberId(member.getId())
                    .productId(product.getId())
                    .orderId(orders.getId())
                    .totalPrice(dto.getAmount() * product.getPrice())
                    .build();
        }

        throw new IllegalArgumentException("주문 실패");
    }

    public OrderResponseDto responsePayment(PayResponseMessage message) {
        Product product = productService.findById(message.getProductId());
        Orders order = findById(message.getOrderId());

        if (!message.isStatus()) {
            product.plusStock(order.getAmount());
            orderRepository.delete(order); // 주문 취소
        } else {
            order.finishPayment();
            return OrderResponseDto.builder()
                    .status(message.isStatus())
                    .orderId(order.getId())
                    .build();
        }

        return OrderResponseDto.builder().status(message.isStatus()).build();
    }

    public Orders findById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow();
    }
}
