package com.example.jpa.demo.repository;

import com.example.jpa.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    public List<Student> findByLastName(String lastName);

    public List<Student> findByLastNameContaining(String lastName);

    public List<Student> findByGuardianName(String guardianName);

    public Student findByFirstNameAndLastName(String firstName, String lastName);

    //JPQL - Deals with java classes. Not with tables.
    @Query("select s from Student s where s.emailId = ?1")
    public Student findStudentByEmailId(String emailId);

    @Query("select s.firstName from Student s where s.emailId = ?1")
    public String findStudentNameByEmailId(String emailId);

    //Native Query - Directly deals with tables. When we want to work with complex queries, we can go for native query.
    @Query(
            value="SELECT * FROM tbl_student s where s.email_address = ?1",
            nativeQuery = true
    )
    public Student findStudentByEmailIdNative(String emailId);

    //Native Query with NamedParam
    @Query(
            value="SELECT * FROM tbl_student s where s.email_address = :email",
            nativeQuery = true
    )
    public Student findStudentByEmailIdNativeNamedParam(@Param("email") String emailId);


    @Modifying  // To indicate that this method perform modify relate operations
    @Transactional  // Indicates that this method deals with transaction and ensures that once transaction completed, commit it. if any transaction failed, it will roll back changes.
    //The best practise is that use @Transactional in service class so that we can update multiple tables or repositories in a single method
    //(if we add @Transactional in that method - it applicable for repos/tables used inside the method)
    //we can use @Transactional in both class and method level.
    @Query(
            value = "UPDATE tbl_student SET first_name=?1 WHERE email_address=?2",
            nativeQuery = true
    )
    public int updateStudent(String firstName, String emailId);

}
