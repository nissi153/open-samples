package com.example.myproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private Long memberNo;
    private String memberId;
    private String memberPw;
    private String phone;
    private LocalDateTime joinDate;
}
