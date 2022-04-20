package com.hanghae99.miniproject_re.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HobbyRequestDto {
    private String title;
    private String nickname;
    private String content;
//    private MultipartFile multipartFile; // param으로 받음
}
