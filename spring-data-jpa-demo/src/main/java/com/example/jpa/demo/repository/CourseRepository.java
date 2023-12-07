package com.example.jpa.demo.repository;

import com.example.jpa.demo.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {

    Page findByTitleContaining(String title, Pageable pageable);

}
