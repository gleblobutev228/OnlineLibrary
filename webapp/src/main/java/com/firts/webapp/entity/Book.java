package com.firts.webapp.entity;

import com.firts.webapp.enums.BookStatus;
import jakarta.persistence.*;

@Entity
@Table(name="book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="book_title", nullable = false, unique = true)
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    public Book(){}

    public Book(String title, Author author){
        this.title = title;
        this.author = author;
        this.status = BookStatus.AVAILABLE;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return this.title;
    }

    public Author getAuthor() {
        return this.author;
    }

    public BookStatus getStatus() {
        return status;
    }
}
