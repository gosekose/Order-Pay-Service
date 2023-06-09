package com.server.order.service.broker;

import com.server.order.service.message.PayResponseMessage;
import com.server.order.service.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderRabbitConsumer {

    private final OrderService orderService;

    @RabbitListener(queues = "#{payQueue.name}")
    public void payResponse(PayResponseMessage message) {
        try {
            log.info("response 도착");
            orderService.responsePayment(message);
        } catch (Exception e) {
            log.error("Error processing message", e);
        }
    }
}
