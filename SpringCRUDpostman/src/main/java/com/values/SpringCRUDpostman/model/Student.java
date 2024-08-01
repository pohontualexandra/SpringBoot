package com.values.SpringCRUDpostman.model;

import jakarta.persistence.*;

@Entity //mark a class as a JPA entity
@Table(name = "studente") //automatically creates the table studente
public class Student {
    @Id //matricola come id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)//qui non serve perche matricola viene inserita
    @Column(unique=true, nullable = false)
    private String matricola;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    @Column(name="codice_fiscale", nullable = false)
    private String codiceFiscale;
    @Column(nullable = false)
    private String email;
    @Column
    private String nomeCorso;
    @Column
    private String password;

    public Student(){

    }

    public Student(String matricola, String nome, String cognome, String codiceFiscale, String email, String nomeCorso, String password) {
        this.matricola = matricola;
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.email = email;
        this.nomeCorso = nomeCorso;
        this.password = password;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeCorso() {
        return nomeCorso;
    }

    public void setNomeCorso(String nomeCorso) {
        this.nomeCorso = nomeCorso;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "matricola='" + matricola + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", codiceFiscale='" + codiceFiscale + '\'' +
                ", email='" + email + '\'' +
                ", nomeCorso='" + nomeCorso + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
