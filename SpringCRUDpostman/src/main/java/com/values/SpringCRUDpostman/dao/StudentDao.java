package com.values.SpringCRUDpostman.dao;

import com.values.SpringCRUDpostman.model.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDao{
    List<Student> getAllStudent() throws SQLException;
    Student getStudentByMatricola(String matricola) throws SQLException;
    Student updateStudentByMatricola(String matricola, Student request) throws SQLException;

    int deleteStudent(String matricola) throws SQLException;
    Student insertStudent(Student request) throws SQLException;
}
