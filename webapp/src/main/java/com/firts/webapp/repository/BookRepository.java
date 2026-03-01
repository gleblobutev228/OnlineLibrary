package com.firts.webapp.repository;

import com.firts.webapp.entity.Book;
import com.firts.webapp.enums.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByStatus(BookStatus status);
    boolean existsByTitle(String title);
    Book findByTitle(String title);
}
