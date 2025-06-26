package com.deeroot.quickstart.controllers;

import com.deeroot.quickstart.TestDataUtil;
import com.deeroot.quickstart.domain.dto.BookDto;
import com.deeroot.quickstart.domain.entities.BookEntity;
import com.deeroot.quickstart.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@Transactional
@Rollback
public class BookControllerIntegrationTests {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private BookService bookService;


    @Autowired
    public BookControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper, BookService bookService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.bookService = bookService;
    }

    @Test
    public void testThatCreateUpdateBookReturnsHttpStatus201Created() throws Exception {
        BookDto bookDto = TestDataUtil.createTestBookDtoA(null);
        String createBookJson = objectMapper.writeValueAsString(bookDto);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateBookReturnsCreateUpdateBook() throws Exception {
        BookDto bookDto = TestDataUtil.createTestBookDtoA(null);
        String createBookJson = objectMapper.writeValueAsString(bookDto);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(bookDto.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle())
        );
    }

    @Test
    public void testThatListBooksReturnsHttp200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatListBooksReturnsBooks() throws Exception {
        BookEntity book = TestDataUtil.createTestBookEntityA(null);
        bookService.createUpdateBook(book.getIsbn(), book);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.content[0].isbn").value(book.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.content[0].title").value(book.getTitle())
        );
    }

    @Test
    public void testThatFindOneReturnsHttp200WhenBookExists() throws Exception {
        BookEntity bookEntity = TestDataUtil.createTestBookEntityA(null);
        bookService.createUpdateBook(bookEntity.getIsbn(), bookEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/" + bookEntity.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatFindOneReturnsBookWhenBookExists() throws Exception {
        BookEntity bookEntity = TestDataUtil.createTestBookEntityA(null);
        bookService.createUpdateBook(bookEntity.getIsbn(), bookEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/" + bookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(bookEntity.getIsbn())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.title").value(bookEntity.getTitle())
        );

    }


    @Test
    public void testThatFindOneReturnsHttp404WhenBookDoesNotExist() throws Exception {
        BookEntity bookEntity = TestDataUtil.createTestBookEntityA(null);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/" + "99")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatUpdateBookReturnsHttpStatus200() throws Exception {
        BookEntity testBookEntityA = TestDataUtil.createTestBookEntityA(null);
        BookEntity savedBook = bookService.createUpdateBook(testBookEntityA.getIsbn(), testBookEntityA);

        BookDto testBookDto = TestDataUtil.createTestBookDtoA(null);
        testBookDto.setIsbn(savedBook.getIsbn());
        String bookJson = objectMapper.writeValueAsString(testBookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + testBookEntityA.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void testThatUpdateBookReturnsUpdatedBook() throws Exception {
        BookEntity testBookEntityA = TestDataUtil.createTestBookEntityA(null);
        BookEntity savedBook = bookService.createUpdateBook(testBookEntityA.getIsbn(), testBookEntityA);

        BookDto testBookDto = TestDataUtil.createTestBookDtoA(null);
        testBookDto.setIsbn(savedBook.getIsbn());
        testBookDto.setTitle("UPDATED");
        String bookJson = objectMapper.writeValueAsString(testBookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + savedBook.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(savedBook.getIsbn())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.title").value("UPDATED")
        );
    }


    @Test
    public void testThatPartialUpdateBookReturnsHttp200() throws Exception {
        BookEntity testBookEntityA = TestDataUtil.createTestBookEntityA(null);
        BookEntity savedBook = bookService.createUpdateBook(testBookEntityA.getIsbn(), testBookEntityA);

        BookDto testBookDto = TestDataUtil.createTestBookDtoA(null);

        testBookDto.setTitle("UPDATED");
        String bookJson = objectMapper.writeValueAsString(testBookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/books/" + savedBook.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void testThatPartialUpdateBookReturnsUpdatedBook() throws Exception {
        BookEntity testBookEntityA = TestDataUtil.createTestBookEntityA(null);
        BookEntity savedBook = bookService.createUpdateBook(testBookEntityA.getIsbn(), testBookEntityA);

        BookDto testBookDto = TestDataUtil.createTestBookDtoA(null);

        testBookDto.setTitle("UPDATED");
        String bookJson = objectMapper.writeValueAsString(testBookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/books/" + savedBook.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(savedBook.getIsbn())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.title").value("UPDATED")
        );
    }

    @Test
    public void testThatDeleteNonExistingBookReturnsHttp204NoContent() throws Exception {


        mockMvc.perform(
                MockMvcRequestBuilders.delete("/books/123123")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatDeleteExistingBookReturnsHttp204NoContent() throws Exception {
        BookEntity testBookEntityA = TestDataUtil.createTestBookEntityA(null);
        BookEntity savedBook = bookService.createUpdateBook(testBookEntityA.getIsbn(), testBookEntityA);


        mockMvc.perform(
                MockMvcRequestBuilders.delete("/books/" + savedBook.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}