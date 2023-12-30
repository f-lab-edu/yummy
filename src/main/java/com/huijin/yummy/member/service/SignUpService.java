package com.huijin.yummy.member.service;

import com.huijin.yummy.member.entity.Member;
import com.huijin.yummy.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    private final MemberRepository memberRepository;

    @Autowired
    public SignUpService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public int signUp(Member member) {
        try {
            memberRepository.save(member);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
}
