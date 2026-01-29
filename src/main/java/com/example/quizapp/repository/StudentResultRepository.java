package com.example.quizapp.repository;

import com.example.quizapp.model.StudentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentResultRepository extends JpaRepository<StudentResult, Long> {
    List<StudentResult> findTop10ByOrderByScoreDesc();
}