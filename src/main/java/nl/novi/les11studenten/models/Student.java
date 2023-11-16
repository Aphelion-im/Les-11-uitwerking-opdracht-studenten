// https://www.youtube.com/watch?v=8SGI_XS5OPw (Amigoscode, Spring Boot Tutorial | Spring Data JPA | 2021)
// @NotNull(message = "This isn't allowed to be Null ") ?
package nl.novi.les11studenten.models;

import jakarta.persistence.*;

// Amigoscode: @Entity: It's good practice to be explicit about the name in combination with the name of the class. Ook in het geval van lange class namen, kun je een korte Entity naam kiezen.
@Entity(name = "Student")
@Table(name = "Students")
public class Student {

    @Id
    @GeneratedValue
    @Column(
            name = "student_nr",
            updatable = false
    )
    private Long studentNr;
    @Column(name = "full_name",
            nullable = false, // NOT NULL
            columnDefinition = "TEXT" // Data TYPE = TEXT ipv Varchar(255)
//            unique = true
    )
    private String fullName;
    @Column(name = "phone_number")
    private int phoneNumber;

    // 2x Constructors/ Lombok: 1x @NoArgsConstructor en 1x @AllArgsConstructor
    // Waar zijn deze constructors voor nodig in Spring Boot?
    public Student(Long studentNr, String fullName, int phoneNumber) {
        this.studentNr = studentNr;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public Student() {

    }

    // Getters & Setters
    // Nodig om JSON objecten goed te kunnen posten en JSON in de response
    public Long getStudentNr() {
        return studentNr;
    }

    public void setStudentNr(Long studentNr) {
        this.studentNr = studentNr;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
