package com.example.coursemanagementsystem.endpoint;

import com.example.coursemanagementsystem.dto.CourseDTO;
import com.example.coursemanagementsystem.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/api/courses")
public class CourseEndpoint {

    private final CourseService courseService;

    @GetMapping
    public List<CourseDTO> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {

        CourseDTO course = courseService.getCourseById(id);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(course);
    }

    @PostMapping
    public CourseDTO createCourse(@RequestBody CourseDTO courseDTO) {
        return courseService.createCourse(courseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id,
                                                  @RequestBody CourseDTO courseDTO) {

        return ResponseEntity.ok(courseService.updateCourse(id, courseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CourseDTO> deleteCourse(@PathVariable Long id) {

        CourseDTO byId = courseService.getCourseById(id);
        if (byId == null) {
            return ResponseEntity.notFound().build();
        }
        courseService.deleteCourse(id);
        return ResponseEntity.ok().build();
    }
}
