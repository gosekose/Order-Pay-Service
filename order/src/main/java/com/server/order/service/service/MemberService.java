package com.server.order.service.service;

import com.server.order.domain.Member;
import com.server.order.repository.MemberRepository;
import com.server.order.service.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long register(MemberDto memberDto) {
        if (memberRepository.findMemberByEmail(memberDto.getEmail()) == null) {
            Member member = memberRepository
                    .save(Member.builder().email(memberDto.getEmail()).password(memberDto.getPassword()).build());
            return member.getId();
        }
        throw new IllegalArgumentException("register error");
    }

    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow();
    }
}
