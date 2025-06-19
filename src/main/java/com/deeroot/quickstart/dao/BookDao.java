package com.deeroot.quickstart.dao;

import com.deeroot.quickstart.domain.Book;

import java.util.Optional;

public interface BookDao {
    void create(Book book);

    Optional<Book> find(String isbn);
}
