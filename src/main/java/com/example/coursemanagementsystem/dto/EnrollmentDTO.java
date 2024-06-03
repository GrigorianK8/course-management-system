package com.example.coursemanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDTO {

    private Long id;
    private Long studentId;
    private Long courseId;
    private LocalDate enrollmentDate;
}
