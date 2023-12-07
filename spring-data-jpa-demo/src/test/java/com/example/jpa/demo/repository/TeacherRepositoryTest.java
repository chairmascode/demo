package com.example.jpa.demo.repository;

import com.example.jpa.demo.entity.Course;
import com.example.jpa.demo.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher(){
        Course JavaCourse = Course.builder()
                .title(".Net")
                .credit(4)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("Jhon")
                .lastName("William")
                //.courses(List.of(JavaCourse))
                .build();
        teacherRepository.save(teacher);
    }

}