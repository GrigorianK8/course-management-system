package com.example.coursemanagementsystem.endpoint;

import com.example.coursemanagementsystem.dto.InstructorDTO;
import com.example.coursemanagementsystem.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/api/instructors")
public class InstructorEndpoint {

    private final InstructorService instructorService;

    @GetMapping
    public List<InstructorDTO> getAllInstructors() {
        return instructorService.getAllInstructors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstructorDTO> getInstructorById(@PathVariable Long id) {

        InstructorDTO instructor = instructorService.getInstructorById(id);
        if (instructor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(instructor);
    }

    @PostMapping
    public InstructorDTO createInstructor(@RequestBody InstructorDTO instructorDTO) {
        return instructorService.createInstructor(instructorDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstructorDTO> updateInstructor(@PathVariable Long id,
                                                          @RequestBody InstructorDTO instructorDTO) {

        return ResponseEntity.ok(instructorService.updateInstructor(id, instructorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<InstructorDTO> deleteInstructor(@PathVariable Long id) {
        InstructorDTO byId = instructorService.getInstructorById(id);
        if (byId == null) {
            return ResponseEntity.notFound().build();
        }
        instructorService.deleteInstructor(id);
        return ResponseEntity.ok().build();
    }
}
