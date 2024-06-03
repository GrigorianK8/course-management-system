package com.example.coursemanagementsystem.service.impl;

import com.example.coursemanagementsystem.dto.StudentDTO;
import com.example.coursemanagementsystem.entity.Student;
import com.example.coursemanagementsystem.mapper.StudentMapper;
import com.example.coursemanagementsystem.repository.StudentRepository;
import com.example.coursemanagementsystem.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;


    @Override
    public List<StudentDTO> getAllStudents() {
        log.info("Fetching all students");
        return studentRepository.findAll().stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElse(null);
        if (student == null) {
            return null;
        }
        log.info("Fetching student with id {}", id);
        return studentMapper.toDto(student);
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = studentMapper.toEntity(studentDTO);
        Student saveStudent = studentRepository.save(student);
        log.info("Student created with id {}", saveStudent.getId());

        return studentMapper.toDto(saveStudent);
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        log.info("Updating student with id {}", id);
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    existingStudent.setName(studentDTO.getName());
                    existingStudent.setEmail(studentDTO.getEmail());
                    Student updatedStudent = studentRepository.save(existingStudent);
                    return studentMapper.toDto(updatedStudent);
                })
                .orElseThrow(() -> {
                    log.info("Student not found with id {}", id);
                    return new NoSuchElementException("Student not found with id " + id);
                });
    }

    @Override
    public void deleteStudent(Long id) {
        log.info("Deleting student with id {}", id);
        studentRepository.deleteById(id);
        log.info("Student deleted with id {}", id);
    }
}
