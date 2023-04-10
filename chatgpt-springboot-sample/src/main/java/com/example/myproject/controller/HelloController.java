package com.example.myproject.controller;

import com.example.myproject.dto.MemberDto;
import com.example.myproject.dto.MemberDtoMapper;
import com.example.myproject.entity.MemberEntity;
import com.example.myproject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HelloController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/")
    @ResponseBody
    public String hello() {
        return "Hello, world!";
    }

    @GetMapping("/members")
    public String getAllMembers(Model model) {
        List<MemberEntity> memberEntities = memberService.getAllMembers();
        List<MemberDto> memberDtos = memberEntities.stream()
                .map(MemberDtoMapper::fromEntity)
                .collect(Collectors.toList());

        model.addAttribute("members", memberDtos);
        return "list";
    }
}

