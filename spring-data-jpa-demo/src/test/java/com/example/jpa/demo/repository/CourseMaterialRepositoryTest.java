package com.example.jpa.demo.repository;

import com.example.jpa.demo.entity.Course;
import com.example.jpa.demo.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    CourseMaterialRepository repository;

    @Test
    public void saveCourseMaterial(){
        Course course = Course.builder()
                .title("Python")
                .credit(2)
                .build();
        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.coursematerial.com")
                .course(course)
                .build();
        repository.save(courseMaterial);
    }

    @Test
    public void getAllCourseMaterials(){
        List<CourseMaterial> courseMaterials = repository.findAll();
        System.out.println("Course Materials:"+courseMaterials);

    }

}