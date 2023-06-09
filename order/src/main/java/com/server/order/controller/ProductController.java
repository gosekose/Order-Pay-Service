package com.server.order.controller;

import com.server.order.controller.dto.ResponseBody;
import com.server.order.service.dto.ProductDto;
import com.server.order.service.service.ProductService;
import com.server.order.service.service.dto.ProductViewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<ResponseBody> saveStock(@RequestBody ProductDto dto) {
        return ResponseEntity.ok().body(new ResponseBody<>(productService.save(dto)));
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseBody> getProduct(Pageable pageable) {
        return ResponseEntity.ok().body(new ResponseBody(productService.getProduct(pageable)));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ResponseBody<ProductViewDto>> getProductDetail(@PathVariable("productId") Long productId) {
        return ResponseEntity.ok().body(new ResponseBody<>(productService.getProductDetail(productId)));
    }
}
