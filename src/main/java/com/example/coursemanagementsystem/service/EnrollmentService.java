package com.example.coursemanagementsystem.service;

import com.example.coursemanagementsystem.dto.EnrollmentDTO;

import java.util.List;

public interface EnrollmentService {

    List<EnrollmentDTO> getAllEnrollments();

    EnrollmentDTO getEnrollmentById(Long id);

    EnrollmentDTO createEnrollment(EnrollmentDTO enrollmentDTO);

    EnrollmentDTO updateEnrollment(Long id, EnrollmentDTO enrollmentDTO);

    void deleteEnrollment(Long id);
}
