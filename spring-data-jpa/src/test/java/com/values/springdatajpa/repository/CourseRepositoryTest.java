package com.values.springdatajpa.repository;

import com.values.springdatajpa.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository repository;

    @Test
    public void saveCourse(){
        Course course1 = Course.builder()
                .title("French Intermediate")
                .credit(8)
                .build();
        repository.save(course1);
    }
    @Test
    public void printCourses(){
        //we print the course material as well, bidirectional mapping
        List<Course> courses =
                repository.findAll();
        System.out.println("All courses: " + courses);
    }
}