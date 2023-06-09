package com.server.order.controller;

import com.server.order.controller.dto.ResponseBody;
import com.server.order.service.dto.ProductDto;
import com.server.order.service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<ResponseBody> saveStock(@RequestBody ProductDto dto) {
        return ResponseEntity.ok().body(new ResponseBody<>(productService.save(dto)));
    }
}
