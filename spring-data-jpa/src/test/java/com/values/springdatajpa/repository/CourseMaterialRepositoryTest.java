package com.values.springdatajpa.repository;

import com.values.springdatajpa.entity.Course;
import com.values.springdatajpa.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository repository;

    @Test
    public void saveCourseMaterial(){
        //cascading - pass the permissions to the child element
        Course course_1 = Course.builder()
                .title("German Advanced")
                .credit(6)
                .build();
        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("https://preply.com/en/courses/german/german-course-for-adults")
                .course(course_1)
                .build();
        repository.save(courseMaterial);
    }

    @Test
    public void printAllCourseMaterials(){
        List<CourseMaterial> courseMaterials =
                repository.findAll();
        System.out.println("All course materials: " + courseMaterials);
    }
}