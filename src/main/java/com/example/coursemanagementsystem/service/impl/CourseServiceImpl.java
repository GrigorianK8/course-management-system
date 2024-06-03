package com.example.coursemanagementsystem.service.impl;

import com.example.coursemanagementsystem.dto.CourseDTO;
import com.example.coursemanagementsystem.entity.Course;
import com.example.coursemanagementsystem.mapper.CourseMapper;
import com.example.coursemanagementsystem.repository.CourseRepository;
import com.example.coursemanagementsystem.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;


    @Override
    public List<CourseDTO> getAllCourses() {
        log.info("Fetching all courses");
        return courseRepository.findAll().stream()
                .map(courseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElse(null);
        if (course == null) {
            return null;
        }
        log.info("Fetching course with id {}", id);
        return courseMapper.toDto(course);
    }

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = courseMapper.toEntity(courseDTO);
        Course saveCourse = courseRepository.save(course);
        log.info("Course created with id {}", saveCourse.getId());

        return courseMapper.toDto(saveCourse);
    }

    @Override
    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        log.info("Updating course with id {}", id);
        return courseRepository.findById(id)
                .map(existingCourse -> {
                    existingCourse.setTitle(courseDTO.getTitle());
                    existingCourse.setDescription(courseDTO.getDescription());
                    Course updatedCourse = courseRepository.save(existingCourse);
                    return courseMapper.toDto(updatedCourse);
                })
                .orElseThrow(() -> {
                    log.info("Course not found with id {}", id);
                    return new NoSuchElementException("Course not found with id " + id);
                });
    }

    @Override
    public void deleteCourse(Long id) {
        log.info("Deleting Course with id {}", id);
        courseRepository.deleteById(id);
        log.info("Course deleted with id {}", id);
    }
}
