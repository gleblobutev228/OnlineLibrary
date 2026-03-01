package com.firts.webapp.dto;


import com.firts.webapp.entity.Author;
import jakarta.validation.constraints.NotBlank;


public class BookForm {

    @NotBlank()
    private String title;

    @NotBlank()
    private String AuthorName;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getAuthorName() {
        return AuthorName;
    }

    public void setAuthorName(String authorName) {
        AuthorName = authorName;
    }
}
