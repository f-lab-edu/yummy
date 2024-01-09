package com.huijin.yummy.member.service;

import com.huijin.yummy.member.entity.Member;
import com.huijin.yummy.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final MemberRepository memberRepository;

    @Autowired
    public LoginService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public boolean login(String email, String password) {
        Member member = memberRepository.findByEmail(email);
        return member != null && member.getPassword().equals(password);
    }
}
