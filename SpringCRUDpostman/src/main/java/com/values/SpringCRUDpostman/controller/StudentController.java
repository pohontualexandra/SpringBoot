package com.values.SpringCRUDpostman.controller;

import com.values.SpringCRUDpostman.dao.StudentDao;
import com.values.SpringCRUDpostman.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController //shortcut for combining two other annotations: @Controller and @ResponseBody.
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    //create rest API get all the students
    //localhost:8080/students
    @Autowired
    StudentDao dao;

    @GetMapping("/students")//provide the url
    public List<Student> getAllStudents(){
        try {
            return dao.getAllStudent();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/student/{matricola}")
    public ResponseEntity<Student> get(@PathVariable String matricola) throws SQLException{
        try{
            Student student = dao.getStudentByMatricola(matricola);
            return ResponseEntity.ok(student);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }catch (Exception e) {
            return ResponseEntity.notFound().build(); // Return 404 Not Found
        }
    }

    @PutMapping("/studentupdate/{matricola}")
    public ResponseEntity<Student> updateStudent(@PathVariable String matricola, @RequestBody Student request) throws SQLException {
        try {
            Student updatedStudent = dao.updateStudentByMatricola(matricola, request);
            return ResponseEntity.ok(updatedStudent);
        } catch (Exception e) {
            logger.error("Errore di sistema", e);
            return ResponseEntity.internalServerError().build();
        }
    }
    @DeleteMapping("/delete/{matricola}")
    public String deleteSt(@PathVariable String matricola) throws SQLException{
        if(dao.deleteStudent(matricola)== 1){
            return "success";
        }else{
            return "error";
        }
    }
    @PostMapping("/insertstudent")
    public ResponseEntity<Student> insertStudent(@RequestBody Student request) throws SQLException {
        Student newStudent = dao.insertStudent(request);

        if (newStudent != null) {
            System.out.println("hello");
            return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
