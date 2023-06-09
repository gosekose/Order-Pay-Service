package com.server.order.controller;

import com.server.order.controller.dto.ResponseBody;
import com.server.order.service.dto.MemberDto;
import com.server.order.service.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<ResponseBody> register(@RequestBody MemberDto memberDto) {
        return ResponseEntity.ok().body(new ResponseBody<>(memberService.register(memberDto)));
    }

}
