package com.hanghae99.miniproject_re.model;

import com.hanghae99.miniproject_re.dto.HobbyRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
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
    private String content;

    public Hobby(HobbyRequestDto hobbyRequestDto) {
        this.title = hobbyRequestDto.getTitle();
        this.nickname = hobbyRequestDto.getNickname();
        this.content = hobbyRequestDto.getContent();
    }

    public void update(HobbyRequestDto hobbyRequestDto){
        this.title = hobbyRequestDto.getTitle();
        this.nickname = hobbyRequestDto.getNickname();
        this.content = hobbyRequestDto.getContent();
    }
}
