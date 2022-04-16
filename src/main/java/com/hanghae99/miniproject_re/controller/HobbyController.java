package com.hanghae99.miniproject_re.controller;

import com.hanghae99.miniproject_re.dto.HobbyRequestDto;
import com.hanghae99.miniproject_re.dto.StatusResponseDto;
import com.hanghae99.miniproject_re.model.Hobby;
import com.hanghae99.miniproject_re.service.HobbyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api") // 요청에 대해 어떤 Controller, 어떤 메소드가 처리할지를 맵핑하기 위한 어노테이션 > 공통적인 url
public class HobbyController {

    @Autowired // 의존성 주입 > 없으면 NPE 발생
    private HobbyService hobbyService;

    @PostMapping("/hobby")
    public StatusResponseDto createHobby (@RequestBody HobbyRequestDto hobbyRequestDto) {
        return hobbyService.postHobby(hobbyRequestDto);
    }

    @GetMapping("/hobbies")
    public List<Hobby> showHobby() {
        return hobbyService.getHobby();
    }

    @GetMapping("/hobby/{hobbyId}")
    public Hobby showHobbyDetail(@PathVariable Integer hobbyId) {
        return hobbyService.getHobbyDetail(hobbyId);
    }

    @PutMapping("/hobby/{hobbyId}")
    public StatusResponseDto updateHobby(@PathVariable Integer hobbyId, @RequestBody HobbyRequestDto hobbyRequestDto) {
        return hobbyService.putHobby(hobbyId, hobbyRequestDto);
    }

    @DeleteMapping("/hobby/{hobbyId}")
    public StatusResponseDto delelteHobby(@PathVariable Integer hobbyId){
        return hobbyService.delHobby(hobbyId);
    }
}
