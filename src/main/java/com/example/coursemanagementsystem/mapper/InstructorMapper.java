package com.example.coursemanagementsystem.mapper;

import com.example.coursemanagementsystem.dto.InstructorDTO;
import com.example.coursemanagementsystem.entity.Instructor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InstructorMapper {


    InstructorDTO toDTO(Instructor instructor);
    Instructor toEntity(InstructorDTO instructorDTO);
}
