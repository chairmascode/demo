package com.example.jpa.demo.repository;

import com.example.jpa.demo.entity.Guardian;
import com.example.jpa.demo.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
// @DataJpaTest - Helps to test repository layer. once test is completed, flush the data.
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .firstName("chairma")
                .lastName("prabhu")
                .emailId("chairma@gmail.com")
//                .guardianName("maran")
//                .guardianEmail("maran@gmail.com")
//                .guardianMobile("1234567890")
                .build();
        studentRepository.save(student);

    }

    @Test
    public void saveStudentWithGuardian(){
        Guardian guardian = Guardian.builder()
                .name("mani")
                .email("mani@gmail.com")
                .mobile("0987654321")
                .build();
        Student student = Student.builder()
                .firstName("porul")
                .lastName("selvan")
                .emailId("porul@gmail.com")
                .guardian(guardian)
                .build();
        studentRepository.save(student);
    }


    @Test
    public void getAllStudents(){
        List<Student> studentList = studentRepository.findAll();
        studentList.forEach(System.out::println);

    }

    @Test
    public void getStudentsByName(){
        List<Student> studentList = studentRepository.findByLastName("selvan");
        System.out.println(studentList);
    }

    @Test
    public void getStudentsByNameContaining(){
        List<Student> studentList = studentRepository.findByLastNameContaining("a");
        studentList.forEach(System.out::println);
    }

    @Test
    public void getStudentsByGuardianName(){
        List<Student> studentList = studentRepository.findByGuardianName("mani");
        studentList.forEach(System.out::println);
    }

    @Test
    public void getStudentsByEmailId(){
        Student student = studentRepository.findStudentByEmailId("chairma@qburst.com");
        System.out.println(student);
    }

    @Test
    public void getStudentNameByEmailId(){
        String firstName = studentRepository.findStudentNameByEmailId("chairma@qburst.com");
        System.out.println(firstName);
    }

    @Test
    public void getStudentsByEmailIdUsingNative(){
        Student student = studentRepository.findStudentByEmailIdNative("porul@gmail.com");
        System.out.println(student);
    }

    @Test
    public void getStudentsByEmailIdUsingNativeNamesParam(){
        Student student = studentRepository.findStudentByEmailIdNativeNamedParam("porul@gmail.com");
        System.out.println(student);
    }

    @Test
    public void updateStudent(){
        int result = studentRepository.updateStudent("chairmaa", "chairma@qburst.com");
        System.out.println(result);
    }

}