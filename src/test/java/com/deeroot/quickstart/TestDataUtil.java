package com.deeroot.quickstart;

import com.deeroot.quickstart.domain.dto.AuthorDto;
import com.deeroot.quickstart.domain.dto.BookDto;
import com.deeroot.quickstart.domain.entities.AuthorEntity;
import com.deeroot.quickstart.domain.entities.BookEntity;

public final class TestDataUtil {

    private TestDataUtil() {}

    public static AuthorEntity createTestAuthorA() {
        return AuthorEntity.builder()
                .name("Abigail Rose")
                .age(80)
                .build();
    }
    public static AuthorDto createTestAuthorDtoA() {
        return AuthorDto.builder()
                .name("Abigail Rose")
                .age(80)
                .build();
    }
    public static AuthorEntity createTestAuthorB() {
        return AuthorEntity.builder()
                .name("Dennis Root")
                .age(30)
                .build();
    }
    public static AuthorDto createTestAuthorDtoB() {
        return AuthorDto.builder()
                .name("Dennis Root")
                .age(30)
                .build();
    }
    public static AuthorEntity createTestAuthorC() {
        return AuthorEntity.builder()
                .name("John John")
                .age(40)
                .build();
    }


    public static BookEntity createTestBookEntityA(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .author(authorEntity)
                .build();
    }
    public static BookEntity createTestBookB(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("978-1-2345-6789-1")
                .title("Dumb2")
                .author(authorEntity)
                .build();
    }
    public static BookEntity createTestBookC(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("978-1-2345-6789-2")
                .title("Dumb3")
                .author(authorEntity)
                .build();
    }


    public static BookDto createTestBookDtoA(final AuthorDto authorDto) {
        return BookDto.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .author(authorDto)
                .build();
    }

}
