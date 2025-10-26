package com.example.library_management_system.dto;

import com.example.library_management_system.model.Book;

import java.util.Set;

public class MemberResponse {
    private Long id;
    private String name;
    private Set<String>borrowedBooks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(Set<String> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
