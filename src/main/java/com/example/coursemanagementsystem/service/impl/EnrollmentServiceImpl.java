package com.example.coursemanagementsystem.service.impl;

import com.example.coursemanagementsystem.dto.EnrollmentDTO;
import com.example.coursemanagementsystem.entity.Enrollment;
import com.example.coursemanagementsystem.mapper.EnrollmentMapper;
import com.example.coursemanagementsystem.repository.EnrollmentRepository;
import com.example.coursemanagementsystem.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;


    @Override
    public List<EnrollmentDTO> getAllEnrollments() {
        log.info("Fetching all enrollments");
        return enrollmentRepository.findAll().stream()
                .map(enrollmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EnrollmentDTO getEnrollmentById(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElse(null);
        if (enrollment == null) {
            return null;
        }
        log.info("Fetching enrollment with id {}", id);
        return enrollmentMapper.toDTO(enrollment);
    }

    @Override
    public EnrollmentDTO createEnrollment(EnrollmentDTO enrollmentDTO) {
        Enrollment enrollment = enrollmentMapper.toEntity(enrollmentDTO);
        Enrollment saveEnrollment = enrollmentRepository.save(enrollment);
        log.info("Student created with id {}", saveEnrollment.getId());

        return enrollmentMapper.toDTO(saveEnrollment);
    }

    @Override
    public EnrollmentDTO updateEnrollment(Long id, EnrollmentDTO enrollmentDTO) {
        log.info("Updating enrollment with id {}", id);
        return enrollmentRepository.findById(id)
                .map(existingEnrollment -> {
                    existingEnrollment.setStudent(enrollmentMapper.toEntity(enrollmentDTO).getStudent());
                    existingEnrollment.setCourse(enrollmentMapper.toEntity(enrollmentDTO).getCourse());
                    existingEnrollment.setEnrollmentDate(enrollmentDTO.getEnrollmentDate());
                    Enrollment updatedEnrollment = enrollmentRepository.save(existingEnrollment);
                    return enrollmentMapper.toDTO(updatedEnrollment);
                })
                .orElseThrow(() -> {
                    log.info("Enrollment not found with id {}", id);
                    return new NoSuchElementException("Enrollment not found with id " + id);
                });
    }

    @Override
    public void deleteEnrollment(Long id) {
        log.info("Deleting enrollment with id {}", id);
        enrollmentRepository.deleteById(id);
        log.info("Enrollment deleted with id {}", id);
    }
}
