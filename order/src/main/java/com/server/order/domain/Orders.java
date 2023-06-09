package com.server.order.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Orders extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private Long amount;

    private boolean isPayed;

    @Builder
    public Orders(Member member, Product product, Long amount) {
        this.member = member;
        this.product = product;
        this.amount = amount;
        this.isPayed = false;
    }

    public void finishPayment() {
        isPayed = true;
    }
}
