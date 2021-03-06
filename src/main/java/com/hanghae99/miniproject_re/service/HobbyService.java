package com.hanghae99.miniproject_re.service;

import com.hanghae99.miniproject_re.dto.HobbyRequestDto;
import com.hanghae99.miniproject_re.dto.StatusResponseDto;
import com.hanghae99.miniproject_re.model.Hobby;
import com.hanghae99.miniproject_re.repository.HobbyRepository;
import com.hanghae99.miniproject_re.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class HobbyService {

    @Autowired // 의존성 주입 > 없으면 NPE 발생
    private HobbyRepository hobbyRepository;

    @Autowired
    private S3Service s3Service;
//    private S3Uploader s3Uploader;

    @Autowired
    private ImageRepository imageRepository;

    // 게시글 작성
    public StatusResponseDto postHobby(MultipartFile multipartFile, HobbyRequestDto hobbyRequestDto) {
        String fileName = createFileName(multipartFile.getOriginalFilename());
        String imageUrl = s3Service.uploadFile(multipartFile,fileName,"hobby3");
        System.out.println(fileName);

        Hobby hobby = new Hobby(imageUrl,hobbyRequestDto);
        hobby.setImageUrl(imageUrl);
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
    @Transactional // DB에 반영
    public StatusResponseDto putHobby(Integer hobbyId, MultipartFile multipartFile, HobbyRequestDto hobbyRequestDto){
        // 요청받은 hobbyId 값으로 게시글 객체 찾기
        Hobby hobby = hobbyRepository.findById(hobbyId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 없습니다."));

        // hooby 객체 안의 imgUrl로만 객체 키 파싱 (폴더 경로까지 포함)
        String image = hobby.getImageUrl();
        String [] key = image.split("/");
        String imageParse = key[key.length-2];
        String imageParse2 = key[key.length-1];
        String currentFilePath = imageParse +"/"+ imageParse2; // 기존 파일 객체 키(폴더 경로+UUID)

        String fileName = createFileName(multipartFile.getOriginalFilename());
        String imageUrl = s3Service.updateFile(multipartFile, currentFilePath, fileName, "hobby3");
        Hobby response = new Hobby(imageUrl,hobbyRequestDto);
        // 게시글에 대한 수정내용 업데이트 해주기
        hobby.update(response);
        StatusResponseDto statusResponseDto = new StatusResponseDto(hobbyId, hobby);
        return statusResponseDto;
    }

    // 게시글 삭제
    public StatusResponseDto delHobby(Integer hobbyId){
        hobbyRepository.deleteById(hobbyId);
        StatusResponseDto statusResponseDto = new StatusResponseDto(hobbyId);
        return statusResponseDto;
    }

    public String createFileName(String fileName) {
        // 먼저 파일 업로드 시, 파일명을 난수화하기 위해 random으로 돌립니다.
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    private String getFileExtension(String fileName) {
        // file 형식이 잘못된 경우를 확인하기 위해 만들어진 로직이며,
        // 파일 타입과 상관없이 업로드할 수 있게 하기 위해 .의 존재 유무만 판단
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
        }

    }
}
