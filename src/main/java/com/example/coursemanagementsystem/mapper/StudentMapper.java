package com.example.coursemanagementsystem.mapper;

import com.example.coursemanagementsystem.dto.StudentDTO;
import com.example.coursemanagementsystem.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentDTO toDto(Student student);
    Student toEntity(StudentDTO studentDTO);
}
