package com.hanghae99.miniproject_re.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3Service {

    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    public String bucket;  // S3 버킷 이름

    // 최초 게시글 작성 시 업로드
    public String uploadFile(MultipartFile file, String fileName, String folderName){
//        String fileName = createFileName(file.getOriginalFilename());
        // 폴더 생성
        fileName = folderName + "/" + fileName;
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        try(InputStream inputStream = file.getInputStream()) {
            amazonS3Client.putObject(new PutObjectRequest(bucket,fileName,inputStream,objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            return amazonS3Client.getUrl(bucket, fileName).toString();

        }catch (IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"파일 업로드에 실패하셨습니다");
        }

    }

    // 글 수정 시 기존 s3에 있는 이미지 정보 삭제,
    public String updateFile(MultipartFile multipartFile,String currentFilePath, String fileName, String folderName){
//        String fileName = createFileName(multipartFile.getOriginalFilename()); // 파일명 랜덤화
        // 폴더 생성
        fileName = folderName + "/" + fileName;
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());

        amazonS3Client.deleteObject(new DeleteObjectRequest(bucket,currentFilePath));

        // 기존 파일명과 수정 파일명이 동일했을 때 , 기존 파일 삭제하는 로직(보류)
//        if(!"".equals(currentFilePath) && currentFilePath != null){
//            boolean isExistObject = amazonS3Client.doesObjectExist(bucket,currentFilePath); // false
//
//            if(!isExistObject){
//                amazonS3Client.deleteObject(new DeleteObjectRequest(bucket,currentFilePath));
//            }
//        }

        try(InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3Client.putObject(new PutObjectRequest(bucket,fileName,inputStream,objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            return amazonS3Client.getUrl(bucket, fileName).toString();
        }catch (IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"파일 업로드에 실패하셨습니다");
        }

    }

}
