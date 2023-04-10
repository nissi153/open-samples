package com.example.myproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name="member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Long memberNo;

    @Column(name = "member_id", nullable = false, unique = true)
    private String memberId;

    @Column(name = "member_pw", nullable = false)
    private String memberPw;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "joindate", nullable = false)
    private LocalDateTime joinDate;
}
