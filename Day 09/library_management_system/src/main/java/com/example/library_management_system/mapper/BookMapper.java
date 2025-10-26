package com.example.library_management_system.mapper;

import com.example.library_management_system.dto.BookRequest;
import com.example.library_management_system.dto.BookResponse;
import com.example.library_management_system.model.Author;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    @Autowired
    AuthorRepository authorRepository;
    public Book toRequest(BookRequest request){
        Book book=new Book();
        book.setTitle(request.getTitle());
        book.setStock(request.getStock());
        Author author=authorRepository.findById(request.getAuthorId()).orElseThrow(()->new RuntimeException("Author not found"));
        book.setAuthor(author);
        return book;

    }

    public BookResponse toResponse(Book book){
        BookResponse res=new BookResponse();
        res.setId(book.getId());
        res.setTitle(book.getTitle());
        res.setStock(book.getStock());
        res.setAuthorName(book.getAuthor().getName());
        return res;
    }
}
