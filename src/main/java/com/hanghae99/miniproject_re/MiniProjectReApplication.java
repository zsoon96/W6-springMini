package com.hanghae99.miniproject_re;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableJpaAuditing // Spring Audit 기능 적용
@SpringBootApplication(exclude = SecurityAutoConfiguration.class) // 스프링 시큐리티 보안 해제
public class MiniProjectReApplication {

    // S3
    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.yml,"
            + "classpath:aws.yml";

    // 배포 시, 시간 설정 (디폴트 시간 = 배포 국가 기준)
    @PostConstruct // Bean이 완전히 초기화 된 후, 단 한번만 호출되는 메소드
    public void started() {
        TimeZone.setDefault((TimeZone.getTimeZone("Asia/Seoul")));
    }

    // S3
    // application.yml과 aws.yml 두개의 파일 모두를 설정 파일로 읽어서 사용
    public static void main(String[] args) {
        new SpringApplicationBuilder(MiniProjectReApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }
}
