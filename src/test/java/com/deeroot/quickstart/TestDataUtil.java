package com.deeroot.quickstart;

import com.deeroot.quickstart.domain.Author;
import com.deeroot.quickstart.domain.Book;

public final class TestDataUtil {

    private TestDataUtil() {}

    public static Author createTestAuthorA() {
        return Author.builder()
                .name("Abigail Rose")
                .age(80)
                .build();
    }
    public static Author createTestAuthorB() {
        return Author.builder()
                .name("Dennis Root")
                .age(30)
                .build();
    }
    public static Author createTestAuthorC() {
        return Author.builder()
                .name("John John")
                .age(40)
                .build();
    }


    public static Book createTestBookA(final Author author) {
        return Book.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .author(author)
                .build();
    }
    public static Book createTestBookB(final Author author) {
        return Book.builder()
                .isbn("978-1-2345-6789-1")
                .title("Dumb2")
                .author(author)
                .build();
    }
    public static Book createTestBookC(final Author author) {
        return Book.builder()
                .isbn("978-1-2345-6789-2")
                .title("Dumb3")
                .author(author)
                .build();
    }
}
