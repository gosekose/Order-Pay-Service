package com.server.order.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductDto {

    private String name;
    private Long price;
    private Long amount;

    @Builder
    public ProductDto(String name, Long price, Long amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
}
