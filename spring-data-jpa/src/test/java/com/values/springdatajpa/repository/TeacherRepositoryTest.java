package com.values.springdatajpa.repository;

import com.values.springdatajpa.entity.Course;
import com.values.springdatajpa.entity.Teacher;
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
        Course courseEng = Course.builder()
                .title("English Advanced")
                .credit(7)
                .build();
        Course courseIt = Course.builder()
                .title("Italian Advanced")
                .credit(7)
                .build();
        Teacher teacher = Teacher.builder()
                .firstName("Alessandro")
                .lastName("Gubbiotti")
                .courses(List.of(courseIt, courseEng))
                .build();
        teacherRepository.save(teacher);
    }
}