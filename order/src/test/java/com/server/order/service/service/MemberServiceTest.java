package com.server.order.service.service;

import com.server.order.domain.Member;
import com.server.order.repository.MemberRepository;
import com.server.order.service.dto.MemberDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    @DisplayName("register - find")
    public void register() throws Exception {
        //given
        String email = "kose@naver.com";
        String password = "123456";

        //when
        Long memberId = memberService.register(new MemberDto(email, password));
        Member findMember = memberService.findById(memberId);

        //then
        assertThat(findMember.getId()).isEqualTo(memberId);
        assertThat(findMember.getEmail()).isEqualTo(email);
        assertThat(findMember.getPassword()).isEqualTo(password);
    }
}