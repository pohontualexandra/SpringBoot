package com.values.springdatajpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="tbl_student",
        uniqueConstraints = @UniqueConstraint(
                name="emailid_unique",
                columnNames = "email_address"
        )
)
public class Student {
    @Id //defines the primary key
    @SequenceGenerator( //defines a squence generator
            name = "student_id",
            sequenceName = "student_sequence",
            allocationSize = 1 //contacts the database for every id, not recommended.
                                // Set bigger for pre-allocation and to reduce database calls
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE, //generated automatically
            generator = "student_sequence"
    )
    private Long studentId;
    private String firstName;
    private String lastName;
    @Column(name="email_address",  // redefines the column
            nullable = false)
    private String emailId;
    @Embedded
    private Guardian guardian;
}
