package com.example.myproject.dto;

import com.example.myproject.entity.MemberEntity;

public class MemberDtoMapper {

    public static MemberDto fromEntity(MemberEntity memberEntity) {
        MemberDto memberDto = new MemberDto();
        memberDto.setMemberNo(memberEntity.getMemberNo());
        memberDto.setMemberId(memberEntity.getMemberId());
        memberDto.setMemberPw(memberEntity.getMemberPw());
        memberDto.setPhone(memberEntity.getPhone());
        memberDto.setJoinDate(memberEntity.getJoinDate());
        return memberDto;
    }
}
