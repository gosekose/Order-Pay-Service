package com.server.order.controller;

import com.server.order.controller.dto.ResponseBody;
import com.server.order.service.dto.OrderDto;
import com.server.order.service.dto.OrderForPayDto;
import com.server.order.service.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    /**
     * 결제에 필요한 정보를 서버에 반환
     */
    @PostMapping("")
    public ResponseEntity<ResponseBody<OrderForPayDto>> orderRequest(@RequestBody OrderDto dto) {
        log.info("dto = {}, {}, {}", dto.getMemberId(), dto.getProductId(), dto.getAmount());
        orderService.orderWithPayStatusFalse(dto);
        return ResponseEntity.ok().body(new ResponseBody<>(orderService.orderWithPayStatusFalse(dto)));
    }
}
