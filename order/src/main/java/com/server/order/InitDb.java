package com.server.order;

import com.server.order.domain.Member;
import com.server.order.domain.Product;
import com.server.order.repository.MemberRepository;
import com.server.order.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final ProductRepository productRepository;
        private final MemberRepository memberRepository;

        public void dbInit() {
            memberRepository.save(new Member("gosekose@naver.com", "12345678910"));
            for (int i=1; i <= 30; i++) productRepository.save(new Product("book_" + i, 2000L, 100L));
        }
    }
}
