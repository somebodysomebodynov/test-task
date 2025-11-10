package com.example.testtask.mapper;

import com.example.testtask.dto.NumberCreateDto;
import com.example.testtask.entity.Number;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-07T22:54:44+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class NumberMapperImpl implements NumberMapper {

    @Override
    public Number toEntity(NumberCreateDto createDto) {
        if ( createDto == null ) {
            return null;
        }

        Number.NumberBuilder number = Number.builder();

        number.value( createDto.value() );

        return number.build();
    }
}
