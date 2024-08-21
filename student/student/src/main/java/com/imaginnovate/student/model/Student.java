package com.imaginnovate.student.model;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, message = "First Name must have at least 3 characters")
    private String firstName;

    @NotNull
    @Size(min = 3, message = "Last Name must have at least 3 characters")
    private String lastName;

    @NotNull(message = "DOB is mandatory")
    private LocalDate dob;

    @NotNull
    @Pattern(regexp = "A|B|C", message = "Valid values for section are A, B, and C")
    private String section;

    @NotNull
    @Pattern(regexp = "M|F", message = "Valid values for gender are M or F")
    private String gender;

    @Min(0) @Max(100)
    private Integer marks1;

    @Min(0) @Max(100)
    private Integer marks2;

    @Min(0) @Max(100)
    private Integer marks3;

    private Integer total;

    private Double average;

    private String result;

    // Getters and Setters
    // Custom methods for calculating total, average, and result

    @PrePersist
    @PreUpdate
    public void calculateResults() {
        this.total = (marks1 != null ? marks1 : 0) + (marks2 != null ? marks2 : 0) + (marks3 != null ? marks3 : 0);
        this.average = this.total / 3.0;
        this.result = (marks1 >= 35 && marks2 >= 35 && marks3 >= 35) ? "Pass" : "Fail";
    }

    @AssertTrue(message = "Age should be greater than 15 and less than or equal to 20 years")
    public boolean isAgeValid() {
        if (dob != null) {
            int age = Period.between(dob, LocalDate.now()).getYears();
            return age > 15 && age <= 20;
        }
        return false;
    }


    public void setMarks1(Integer marks1) {
        this.marks1 = marks1;
    }

    public void setMarks2(Integer marks2) {
        this.marks2 = marks2;
    }

    public void setMarks3(Integer marks3) {
        this.marks3 = marks3;
    }
}
