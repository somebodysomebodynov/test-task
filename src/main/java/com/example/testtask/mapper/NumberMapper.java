package com.example.testtask.mapper;

import com.example.testtask.dto.NumberCreateDto;
import com.example.testtask.entity.Number;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NumberMapper {
    Number toEntity(NumberCreateDto createDto);
}
