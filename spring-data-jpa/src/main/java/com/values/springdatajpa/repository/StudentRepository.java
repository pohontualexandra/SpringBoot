package com.values.springdatajpa.repository;

import com.values.springdatajpa.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByFirstName(String firstName);
    List<Student> findByFirstNameContaining(String name);
    List<Student> findByLastNameNotNull();
    List<Student> findByGuardianName(String guardianName);
    //findByFirstNameAndLastName, findByFirstNameOrLastName, findByStartDateBetween ecc

    //JPQL:
    @Query("select s from Student s where s.emailId = ?1")
    Student getStudentByEmailAddress(String emailId);

    //Native
    @Query(
            value = "select * from tbl_student where s.email_address = ?1",
            nativeQuery = true)
    Student getStudentByEmailAddressNative(String emailId);

    //Native Named Param
    @Query(
            value = "select * from tbl_student where email_address = :emailId",
            nativeQuery = true)
    Student getStudentByEmailAddressNativeParam(@Param("emailId")String emailId);

    @Modifying
    @Transactional
    @Query(
            value = "update tbl_student set first_name = ?1 where email_address = ?2",
            nativeQuery = true
    )
    int updateStudentNameByEmailId(String firstName, String emailId);
}
