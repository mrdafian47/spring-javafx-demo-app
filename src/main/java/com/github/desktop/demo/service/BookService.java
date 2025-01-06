package com.github.desktop.demo.service;

import com.github.desktop.demo.entity.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<BookEntity> getBookAll();

    Optional<BookEntity> getBookById(Long id);

    BookEntity createBook(BookEntity entity);

    BookEntity updateBook(BookEntity entity, Long id);

    void deleteBook(Long id);
}
