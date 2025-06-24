package com.deeroot.quickstart.services;

import com.deeroot.quickstart.domain.entities.BookEntity;

public interface BookService {
    BookEntity createBook(String isbn, BookEntity book);
}
