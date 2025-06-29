package com.deeroot.quickstart.mappers.impl;

import com.deeroot.quickstart.domain.dto.AuthorDto;
import com.deeroot.quickstart.domain.dto.BookDto;
import com.deeroot.quickstart.domain.entities.AuthorEntity;
import com.deeroot.quickstart.domain.entities.BookEntity;
import com.deeroot.quickstart.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements Mapper<BookEntity, BookDto> {

    private ModelMapper modelMapper;

    public BookMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BookDto mapTo(BookEntity bookEntity) {
        return modelMapper.map(bookEntity, BookDto.class);
    }

    @Override
    public BookEntity mapFrom(BookDto bookDto) {
        return modelMapper.map(bookDto, BookEntity.class);
    }

}