package com.deeroot.quickstart.services.impl;

import com.deeroot.quickstart.domain.entities.BookEntity;
import com.deeroot.quickstart.repositories.BookRepository;
import com.deeroot.quickstart.services.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity createBook(String isbn, BookEntity book) {
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }


}
