package com.hanghae99.miniproject_re.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 객체의 입장에서 공통 매핑 정보가 필요할 때 사용한다.
@EntityListeners(AuditingEntityListener.class) // 해당 클래스에 Auditing 기능을 포함한다.
public class Timestamped {

//    @CreatedDate
//    private LocalDateTime createdAt;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm") // 날짜 형식 지정 어노테이션
    private LocalDateTime modifiedAt;
}