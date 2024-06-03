package com.example.coursemanagementsystem.endpoint;

import com.example.coursemanagementsystem.dto.EnrollmentDTO;
import com.example.coursemanagementsystem.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/api/enrollments")
public class EnrollmentEndpoint {

    private final EnrollmentService enrollmentService;

    @GetMapping
    public List<EnrollmentDTO> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> getEnrollmentById(@PathVariable Long id) {

        EnrollmentDTO enrollment = enrollmentService.getEnrollmentById(id);
        if (enrollment == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(enrollment);
    }

    @PostMapping
    public EnrollmentDTO createEnrollment(@RequestBody EnrollmentDTO enrollmentDTO) {
        return enrollmentService.createEnrollment(enrollmentDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> updateEnrollment(@PathVariable Long id,
                                                          @RequestBody EnrollmentDTO enrollmentDTO) {

        return ResponseEntity.ok(enrollmentService.updateEnrollment(id, enrollmentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> deleteEnrollment(@PathVariable Long id) {

        EnrollmentDTO byId = enrollmentService.getEnrollmentById(id);
        if (byId == null) {
            return ResponseEntity.notFound().build();
        }
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.ok().build();
    }
}
