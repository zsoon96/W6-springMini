package com.hanghae99.miniproject_re.model;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int hobbyid;

    @Column(nullable = false)
    private String multipartFile; // 기존 파일 삭제를 위한 객체 키

    @Column(nullable = false)
    private String imageUrl; // 리턴해줄 url

    public Image(String fileName, String imageUrl) {
        this.multipartFile = fileName;
        this.imageUrl = imageUrl;
    }
}