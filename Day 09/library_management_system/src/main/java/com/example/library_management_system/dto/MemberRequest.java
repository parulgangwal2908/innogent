package com.example.library_management_system.dto;

import java.util.Set;

public class MemberRequest {
    private String name;
    private Set<Long> borrowedBookIds;


    public String getName() {
        return name;
    }

    public Set<Long> getBorrowedBookIds() {
        return borrowedBookIds;
    }

    public void setBorrowedBookIds(Set<Long> borrowedBookIds) {
        this.borrowedBookIds = borrowedBookIds;
    }

    public void setName(String name) {
        this.name = name;
    }
}
