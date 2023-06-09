package com.server.order.service.service;

import com.server.order.domain.Product;
import com.server.order.service.dto.ProductDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Test
    @DisplayName("상품 저장")
    public void save() throws Exception {
        //given
        String name = "book";
        Long price = 2000L;
        Long amount = 200L;

        //when
        Long productId = productService.save(ProductDto.builder().name(name).price(price).amount(amount).build());
        Product findProduct = productService.findById(productId);

        //then
        assertThat(findProduct.getId()).isEqualTo(productId);
        assertThat(findProduct.getName()).isEqualTo(name);
        assertThat(findProduct.getPrice()).isEqualTo(price);
        assertThat(findProduct.getAmount()).isEqualTo(amount);
    }


}