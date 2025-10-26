package com.example.library_management_system.mapper;

import com.example.library_management_system.dto.AuthorRequest;
import com.example.library_management_system.dto.AuthorResponse;
import com.example.library_management_system.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {
    public Author toRequest(AuthorRequest request){
        Author author=new Author();
        author.setName(request.getName());
        return author;

    }
    public AuthorResponse toResponse(Author author){
        AuthorResponse res=new AuthorResponse();
        res.setId(author.getId());
        res.setName(author.getName());
        return res;

    }
}
