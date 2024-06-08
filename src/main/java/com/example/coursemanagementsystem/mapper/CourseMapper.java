package com.example.coursemanagementsystem.mapper;

import com.example.coursemanagementsystem.dto.CourseDTO;
import com.example.coursemanagementsystem.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseMapper {


    CourseDTO toDto(Course course);
    Course toEntity(CourseDTO courseDTO);
}
