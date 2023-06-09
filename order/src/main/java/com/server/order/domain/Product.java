package com.server.order.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Product extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    private String name;

    private Long price;

    private Long amount;

    @Builder
    public Product(String name, Long price, Long amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public void minusStock(Long amount) {
        this.amount -= amount;
    }

    public void plusStock(Long amount) {
        this.amount += amount;
    }

    public boolean isEnoughAmount(Long neededAmount) {
        return amount >= neededAmount;
    }
}
