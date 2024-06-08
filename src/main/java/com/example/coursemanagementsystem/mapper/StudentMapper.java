package com.example.coursemanagementsystem.mapper;

import com.example.coursemanagementsystem.dto.StudentDTO;
import com.example.coursemanagementsystem.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDTO toDto(Student student);
    Student toEntity(StudentDTO studentDTO);
}
