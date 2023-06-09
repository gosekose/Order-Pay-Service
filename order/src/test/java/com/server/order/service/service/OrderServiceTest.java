package com.server.order.service.service;

import com.server.order.domain.Member;
import com.server.order.domain.Orders;
import com.server.order.domain.Product;
import com.server.order.repository.MemberRepository;
import com.server.order.repository.ProductRepository;
import com.server.order.service.dto.OrderDto;
import com.server.order.service.dto.OrderForPayDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class OrderServiceTest {

    @Autowired OrderService orderService;
    @Autowired MemberRepository memberRepository;
    @Autowired ProductRepository productRepository;

    @Test
    @DisplayName("임시로 isPayed = false로 주문을 만들고 결제 ")
    public void order() throws Exception {
        //given
        Long amount = 10L;
        Member saveMember = memberRepository.save(new Member("kose@naver.com", "123456"));
        Product saveProduct = productRepository.save(new Product("book", 20000L, 100L));

        //when
        OrderForPayDto orderForPayDto = orderService.orderWithPayStatusFalse(
                OrderDto.builder()
                        .memberId(saveMember.getId())
                        .productId(saveProduct.getId())
                        .amount(amount)
                        .build());

        Orders savedOrder = orderService.findById(orderForPayDto.getOrderId());

        //then
        assertThat(savedOrder.isPayed()).isFalse();
        assertThat(savedOrder.getAmount()).isEqualTo(amount);
        assertThat(savedOrder.getMember()).isEqualTo(saveMember);
        assertThat(savedOrder.getProduct()).isEqualTo(saveProduct);
    }

}