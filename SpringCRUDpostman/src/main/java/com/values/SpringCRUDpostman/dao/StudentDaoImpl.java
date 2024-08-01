package com.values.SpringCRUDpostman.dao;

import com.values.SpringCRUDpostman.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class StudentDaoImpl implements StudentDao{

    @Autowired
    private DataSource dataSource;

    public List<Student> getAllStudent() throws SQLException {
        List<Student> listaStudenti = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        String sql = "select * from studente";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        while(result.next()){
            listaStudenti.add(new Student(result.getString("matricola"), result.getString("nome"),
                    result.getString("cognome"),result.getString("codice_fiscale"),
                    result.getString("email"), result.getString("nome_corso"),
                    result.getString("password")));
        }
        return listaStudenti;
    }

    public Student getStudentByMatricola(String matricola) throws SQLException{
        Connection connection = dataSource.getConnection();
        String sql = "select * from studente where matricola=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, matricola);

        ResultSet result = statement.executeQuery();
        if(result.next()){
            return new Student(result.getString("matricola"), result.getString("nome"),
                    result.getString("cognome"),result.getString("codice_fiscale"),
                    result.getString("email"), result.getString("nome_corso"),
                    result.getString("password"));
        }else{
            return null;
        }
    }

    public Student updateStudentByMatricola(String matricola, Student request) throws SQLException{
        Connection connection = dataSource.getConnection();
        String sql = "UPDATE studente " +
                "SET nome = ?, cognome = ?, codice_fiscale = ?, email = ?, nome_corso = ?, password = ? " +
                "WHERE matricola = ?;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, request.getNome());
        ps.setString(2, request.getCognome());
        ps.setString(3, request.getCodiceFiscale());
        ps.setString(4, request.getEmail());
        ps.setString(5, request.getNomeCorso());
        ps.setString(6, request.getPassword());
        ps.setString(7, matricola);
        int rowsUpdated = ps.executeUpdate();

        if (rowsUpdated == 1) {
            return getStudentByMatricola(matricola); // Return the updated student
        } else {
            return null;
        }
    }

    public Student insertStudent(Student request) throws SQLException{
        Connection connection = dataSource.getConnection();
        String sql = "INSERT INTO studente (matricola, nome, cognome, codice_fiscale, email, nome_corso, password) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, request.getMatricola());
        ps.setString(2, request.getNome());
        ps.setString(3, request.getCognome());
        ps.setString(4, request.getCodiceFiscale());
        ps.setString(5, request.getEmail());
        ps.setString(6, request.getNomeCorso());
        ps.setString(7, request.getPassword());
        int result = ps.executeUpdate();
        if (result == 1) {
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs!=null) {
                    return new Student(request.getMatricola(), request.getNome(), request.getCognome(), request.getCodiceFiscale(), request.getEmail(), request.getNomeCorso(), request.getPassword());
                } else {
                    throw new SQLException("Request failed");
                }
            }
        } else {
            throw new SQLException("Student insertion failed");
        }
    }
    public int deleteStudent(String matricola) throws SQLException{
        Connection connection = dataSource.getConnection();
        String sql = "delete from studente where studente.matricola=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, matricola);
        return ps.executeUpdate();
    }
}
