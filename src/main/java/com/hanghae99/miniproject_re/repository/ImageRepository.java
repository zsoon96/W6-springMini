package com.hanghae99.miniproject_re.repository;


import com.hanghae99.miniproject_re.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
