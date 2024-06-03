package com.example.coursemanagementsystem.service.impl;

import com.example.coursemanagementsystem.dto.InstructorDTO;
import com.example.coursemanagementsystem.entity.Instructor;
import com.example.coursemanagementsystem.mapper.InstructorMapper;
import com.example.coursemanagementsystem.repository.InstructorRepository;
import com.example.coursemanagementsystem.service.InstructorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;

    @Override
    public List<InstructorDTO> getAllInstructors() {
        log.info("Fetching all instructors");
        return instructorRepository.findAll().stream()
                .map(instructorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InstructorDTO getInstructorById(Long id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElse(null);
        if (instructor == null) {
            return null;
        }
        log.info("Fetching instructor with id {}", id);
        return instructorMapper.toDTO(instructor);
    }

    @Override
    public InstructorDTO createInstructor(InstructorDTO instructorDTO) {
        Instructor instructor = instructorMapper.toEntity(instructorDTO);
        Instructor saveInstructor = instructorRepository.save(instructor);
        log.info("Instructor created with id {}", saveInstructor.getId());

        return instructorMapper.toDTO(saveInstructor);
    }

    @Override
    public InstructorDTO updateInstructor(Long id, InstructorDTO instructorDTO) {
        log.info("Updating instructor with id {}", id);
        return instructorRepository.findById(id)
                .map(existingInstructor -> {
                    existingInstructor.setName(instructorDTO.getName());
                    existingInstructor.setEmail(instructorDTO.getEmail());
                    Instructor updateInstructor = instructorRepository.save(existingInstructor);
                    return instructorMapper.toDTO(updateInstructor);
                })
                .orElseThrow(() -> {
                    log.info("Instructor not found with id {}", id);
                    return new NoSuchElementException("Instructor not found by id " + id);
                });
    }

    @Override
    public void deleteInstructor(Long id) {
        log.info("Deleting instructor with id {}", id);
        instructorRepository.deleteById(id);
        log.info("Instructor deleted with id {}", id);
    }
}
