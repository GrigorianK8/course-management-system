package com.example.coursemanagementsystem.service;

import com.example.coursemanagementsystem.dto.StudentDTO;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(Long id);

    StudentDTO createStudent(StudentDTO studentDTO);

    StudentDTO updateStudent(Long id, StudentDTO studentDTO);

    void deleteStudent(Long id);
}
