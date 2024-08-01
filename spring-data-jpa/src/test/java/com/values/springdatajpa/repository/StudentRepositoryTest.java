package com.values.springdatajpa.repository;

import com.values.springdatajpa.entity.Guardian;
import com.values.springdatajpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.values.springdatajpa.entity.Student.*;
import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest
@SpringBootTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;


    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .emailId("miriam@gmail.com")
                .firstName("Miriam")
                .lastName("Fleur")
                //.guardianName("Ben")
                //.guardianEmail("bensçgmail.com")
                //.guardianMobile("34893002380")
                .build();
        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian(){
        Guardian guardian = Guardian.builder()
                .name("Jannet")
                .email("jannet12@gmail.com")
                .mobile("372322455")
                .build();
        Student student =   Student.builder()
                .emailId("daisy@gmail.com")
                .firstName("Daisy")
                .lastName("Claire")
                .guardian(guardian)
                .build();
        studentRepository.save(student);
    }

    @Test
    public void printAllStudents(){
        List<Student> studentList =
                studentRepository.findAll();
        System.out.println("Student List : " + studentList);
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> students =
                studentRepository.findByFirstName("Miriam");
        System.out.println("Search Student By Name Result: " + students);
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> students =
                studentRepository.findByFirstNameContaining("ius");
        System.out.println("Search Student By Name Containing String: " + students);
    }

    @Test
    public void printStudentByGuardianName(){
        List<Student> students =
                studentRepository.findByGuardianName("Filippe");
        System.out.println("Search Student By Guardian Name: " + students);
    }

    @Test
    public void printStudentByEmailAddress(){
        Student student =
                studentRepository.getStudentByEmailAddress("flavius@gmail.com");
        System.out.println("Search Student By Email: " + student);
    }
    @Test
    public void printStudentByEmailAddressNative(){
        Student student =
                studentRepository.getStudentByEmailAddressNative("flavius@gmail.com");
        System.out.println("Search Student By Email Native: " + student);
    }
    @Test
    public void printStudentByEmailAddressNativeParam(){
        Student student =
                studentRepository.getStudentByEmailAddressNativeParam("flavius@gmail.com");
        System.out.println("Search Student By Email Native Param: " + student);
    }
    @Test
    public void updateStudentNameByEmailId(){
        String newName = "Miriami";
        int updatedStudent = studentRepository.updateStudentNameByEmailId(newName, "miriam@gmail.com");
        if(updatedStudent != 0){
            System.out.println("Student updated. New name: "+ newName);
        }else{
            System.out.println("Student with the provided email not found.");
        }
    }
}