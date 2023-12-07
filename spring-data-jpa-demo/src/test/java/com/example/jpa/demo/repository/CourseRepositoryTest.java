package com.example.jpa.demo.repository;

import com.example.jpa.demo.entity.Course;
import com.example.jpa.demo.entity.Student;
import com.example.jpa.demo.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void getAllCourses(){
        List<Course> courses = courseRepository.findAll();
        System.out.println(courses);
    }


    @Test
    public void saveCourseWithTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Arun")
                .lastName("Kumar")
                .build();

        Course course = Course.builder()
                .title("Spring")
                .credit(7)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }


    @Test
    public void getDataWithPagination(){
        Pageable firstPaginationWithThreeRecords = PageRequest.of(0,3);
        Pageable secondPaginationWithTwoRecords = PageRequest.of(1,2);

        List<Course> courses = courseRepository.findAll(firstPaginationWithThreeRecords).getContent();
        courses.forEach(System.out::println);

        List<Course> courses2 = courseRepository.findAll(secondPaginationWithTwoRecords).getContent();
        courses2.forEach(System.out::println);

        long totalElements = courseRepository.findAll(firstPaginationWithThreeRecords).getTotalElements();

        int totalPages = courseRepository.findAll(secondPaginationWithTwoRecords).getTotalPages();

        System.out.println(totalElements);
        System.out.println(totalPages);

    }


    @Test
    public void getDataWithPaginationAndSorting(){
        Pageable sortByTitle = PageRequest.of(0,4, Sort.by("title"));
        List<Course> coursesSortedByTitle = courseRepository.findAll(sortByTitle).getContent();
        coursesSortedByTitle.stream().map(course -> course.getTitle()).collect(Collectors.toList()).forEach(System.out::println);

        Pageable sortByCredit = PageRequest.of(0,4, Sort.by("credit").descending());
        List<Course> coursesSortedByCredit = courseRepository.findAll(sortByCredit).getContent();
        coursesSortedByCredit.forEach(course -> System.out.println(course.getCredit()));

        Pageable sortByTitleAndCredit = PageRequest.of(0,4, Sort.by("title").descending().and(Sort.by("credit")));
        List<Course> coursesSortByTitleAndCredit = courseRepository.findAll(sortByTitleAndCredit).getContent();
    }


    @Test
    public void getDataUsingCustomizedPaging(){
        Pageable pageable = PageRequest.of(0,2);
        List<Course> courses = courseRepository.findByTitleContaining("P", pageable).getContent();
        courses.forEach(System.out::println);
    }

    //ManyToMany Mapping - Students and Courses
    @Test
    public void AddCourseWithStudentAndTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Sam")
                .lastName("Anderson")
                .build();
        Student student = Student.builder()
                .firstName("sachin")
                .lastName("teandulkar")
                .emailId("sachin@gmail.com")
                .build();
        Course course = Course.builder()
                .title("WebDevelopment")
                .credit(9)
                .teacher(teacher)
                .build();
        course.addStudents(student);
        courseRepository.save(course);
    }

}