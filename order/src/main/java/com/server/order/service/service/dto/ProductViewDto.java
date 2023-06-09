package com.server.order.service.service.dto;

import com.server.order.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductViewDto {
    private Long productId;
    private String name;
    private Long price;
    private Long amount;

    public ProductViewDto(Product product) {
        this.productId = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.amount = product.getAmount();
    }
}
