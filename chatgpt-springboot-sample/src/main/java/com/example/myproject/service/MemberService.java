package com.example.myproject.service;

import com.example.myproject.entity.MemberEntity;
import com.example.myproject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<MemberEntity> getAllMembers() {
        return memberRepository.findAll();
    }
}
