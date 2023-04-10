package com.example.myproject.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/sample")
@RestController
public class SampleRestController {

    @GetMapping("/{id}")
    public String getSample(@PathVariable Long id) {
        // id를 이용해 데이터를 조회하고 응답합니다.
        return "Sample Data #" + id;
    }

    @PostMapping
    public String createSample(@RequestBody String data) {
        // 요청 바디에 포함된 데이터를 이용해 새로운 데이터를 생성하고 응답합니다.
        return "Created Sample Data: " + data;
    }

    @PutMapping("/{id}")
    public String updateSample(@PathVariable Long id, @RequestBody String data) {
        // id를 이용해 해당 데이터를 업데이트하고 응답합니다.
        return "Updated Sample Data #" + id + ": " + data;
    }

    @DeleteMapping("/{id}")
    public String deleteSample(@PathVariable Long id) {
        // id를 이용해 해당 데이터를 삭제하고 응답합니다.
        return "Deleted Sample Data #" + id;
    }
}
