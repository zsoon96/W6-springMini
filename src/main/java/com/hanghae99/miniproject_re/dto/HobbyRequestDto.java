package com.hanghae99.miniproject_re.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class HobbyRequestDto {
    private String title;
    private String nickname;
    private String content;
    private MultipartFile multipartFile;
}
