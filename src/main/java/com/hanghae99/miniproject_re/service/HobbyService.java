package com.hanghae99.miniproject_re.service;

import com.hanghae99.miniproject_re.dto.HobbyRequestDto;
import com.hanghae99.miniproject_re.dto.StatusResponseDto;
import com.hanghae99.miniproject_re.model.Hobby;
import com.hanghae99.miniproject_re.repository.HobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class HobbyService {

    @Autowired // 의존성 주입 > 없으면 NPE 발생
    private HobbyRepository hobbyRepository;

    // 게시글 작성
    public StatusResponseDto postHobby(HobbyRequestDto hobbyRequestDto){
        Hobby hobby = new Hobby(hobbyRequestDto);
        hobbyRepository.save(hobby);
        // 헤더 값과 데이터를 함께 보내는 dto 생성
        StatusResponseDto statusResponseDto = new StatusResponseDto(hobby);
        return statusResponseDto;
    }

    // 게시글 목록 조회
    public List<Hobby> getHobby(){
        List<Hobby> hobby = hobbyRepository.findAll();
        return hobby;
    }

    // 게시글 상세 조회
    public Hobby getHobbyDetail(Integer hobbyId){
        Hobby hobby = hobbyRepository.findById(hobbyId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 없습니다."));
        return hobby;
    }

    // 게시글 수정
    @Transactional // DB에 반영해주는 아이?
    public StatusResponseDto putHobby(Integer hobbyId, HobbyRequestDto hobbyRequestDto){
        // 요청받은 hobbyId 값으로 게시글 객체 찾기
        Hobby hobby = hobbyRepository.findById(hobbyId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 없습니다."));
        // 게시글에 대한 수정내용 업데이트 해주기
        hobby.update(hobbyRequestDto);
        // 수정한 게시글 DB에 저장하기 --> 이 역할을 @Transactional이 해준다...!!!!!
//        hobbyRepository.save(hobby);
        // 헤더 값과 데이터를 함께 보내는 dto 생성 > 인자가 hobby가 되면 메세지 오류 생김
        // 왠만하면 메소드에서 받는 인자 다 넣어주기 > 받는 인자가 곧 보내는 데이터가 되기때문! = hobbyRequestDto만 넣어도 수정은 문제없긴함..
        StatusResponseDto statusResponseDto = new StatusResponseDto(hobbyId, hobby);
        return statusResponseDto;
    }

    // 게시글 삭제
    public StatusResponseDto delHobby(Integer hobbyId){
        hobbyRepository.deleteById(hobbyId);
        StatusResponseDto statusResponseDto = new StatusResponseDto(hobbyId);
        return statusResponseDto;
    }
}
