package com.hanghae99.miniproject_re.repository;

import com.hanghae99.miniproject_re.model.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyRepository extends JpaRepository<Hobby, Integer> {
}
