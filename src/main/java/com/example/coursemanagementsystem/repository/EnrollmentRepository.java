package com.example.coursemanagementsystem.repository;

import com.example.coursemanagementsystem.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
