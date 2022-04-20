package com.hanghae99.miniproject_re.model;

import com.hanghae99.miniproject_re.dto.HobbyRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Hobby extends Timestamped {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String content;


    public Hobby(String imageUrl, String title, String nickname, String content) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.nickname = nickname;
        this.content = content;
    }

    public Hobby(String imageUrl, HobbyRequestDto hobbyRequestDto) {
        this.imageUrl = imageUrl;
        this.title = hobbyRequestDto.getTitle();
        this.nickname = hobbyRequestDto.getNickname();
        this.content = hobbyRequestDto.getContent();
    }

    public void update(Hobby hobby){
        this.imageUrl = hobby.getImageUrl();
        this.title = hobby.getTitle();
        this.nickname = hobby.getNickname();
        this.content = hobby.getContent();
    }
}
