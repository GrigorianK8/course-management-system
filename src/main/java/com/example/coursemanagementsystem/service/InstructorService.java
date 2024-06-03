package com.example.coursemanagementsystem.service;

import com.example.coursemanagementsystem.dto.InstructorDTO;

import java.util.List;

public interface InstructorService {

    List<InstructorDTO> getAllInstructors();

    InstructorDTO getInstructorById(Long id);

    InstructorDTO createInstructor(InstructorDTO instructorDTO);

    InstructorDTO updateInstructor(Long id, InstructorDTO instructorDTO);

    void deleteInstructor(Long id);
}
