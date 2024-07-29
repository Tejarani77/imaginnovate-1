package com.tejarani.test.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String employeeId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @ElementCollection
    @NotEmpty
    private List<@Pattern(regexp="^\\d{10}$") String> phoneNumbers;

    @NotNull
    private LocalDate doj;

    @NotNull
    @Positive
    private Double salary;

    // Getters and Setters
}

