package nl.novi.les11studenten.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private Long studentNr ;
    private String fullName;
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
