package com.github.desktop.demo.service;

import com.github.desktop.demo.entity.BookEntity;
import com.github.desktop.demo.repository.BookRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;

    @Override
    public List<BookEntity> getBookAll() {
        List<BookEntity> entityList = repository.findAll();
        if (entityList.isEmpty()) {
            createSeedingForBook();
            entityList.addAll(repository.findAll());
        }
        return entityList;
    }

    @Override
    public Optional<BookEntity> getBookById(Long id) {
        return repository.findById(id);
    }

    @Override
    public BookEntity createBook(BookEntity entity) {
        return repository.save(entity);
    }

    @Override
    public BookEntity updateBook(BookEntity entity, Long id) {
        BookEntity updateEntity = repository.findById(id).orElse(new BookEntity());

        updateEntity.setTitle(entity.getTitle());
        updateEntity.setDescription(entity.getDescription());
        updateEntity.setAuthor(entity.getAuthor());
        updateEntity.setGenre(entity.getGenre());
        updateEntity.setPublishYear(entity.getPublishYear());

        return repository.save(entity);
    }

    @Override
    public void deleteBook(Long id) {
        repository.deleteById(id);
    }

    private void createSeedingForBook() {
        List<BookEntity> entityList = Arrays.asList(
                BookEntity.builder()
                        .title("Sapiens: A Brief History of Humankind")
                        .description("A compelling exploration of the history of humanity, from the earliest days of Homo sapiens to the modern era. The book discusses the cognitive, agricultural, and scientific revolutions that shaped human society.")
                        .author("Yuval Noah Harari")
                        .genre("Non-fiction, History, Anthropology")
                        .publishYear(2011)
                        .build(),
                BookEntity.builder()
                        .title("1984")
                        .description("A dystopian novel set in a totalitarian society ruled by Big Brother, where truth is manipulated, and individuality is suppressed. It explores themes of surveillance, propaganda, and resistance.")
                        .author("George Orwell")
                        .genre("Fiction, Dystopian, Political Fiction")
                        .publishYear(1949)
                        .build(),
                BookEntity.builder()
                        .title("Atomic Habits")
                        .description("A practical guide to building good habits and breaking bad ones. The book provides actionable strategies for making small changes that lead to remarkable personal and professional improvements.")
                        .author("James Clear")
                        .genre("Non-fiction, Self-help, Psychology")
                        .publishYear(2018)
                        .build(),
                BookEntity.builder()
                        .title("The Alchemist")
                        .description("A philosophical story of Santiago, a shepherd boy who dreams of discovering treasure in the Egyptian pyramids. The book teaches about following one’s dreams and listening to one’s heart.")
                        .author("Paulo Coelho")
                        .genre("Fiction, Adventure, Philosophy")
                        .publishYear(1988)
                        .build(),
                BookEntity.builder()
                        .title("Dune")
                        .description("A science fiction epic set on the desert planet Arrakis. It follows Paul Atreides as he navigates political intrigue, family dynamics, and the fight for control over the planet’s most valuable resource: spice.")
                        .author("Frank Herbert")
                        .genre("Fiction, Science Fiction, Epic")
                        .publishYear(1965)
                        .build()
        );

        repository.saveAll(entityList);
    }
}
