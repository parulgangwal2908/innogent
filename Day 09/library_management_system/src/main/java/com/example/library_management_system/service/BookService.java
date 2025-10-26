package com.example.library_management_system.service;

import com.example.library_management_system.dto.BookRequest;
import com.example.library_management_system.dto.BookResponse;
import com.example.library_management_system.mapper.BookMapper;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.model.Member;
import com.example.library_management_system.repository.BookRepository;
import com.example.library_management_system.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookMapper bookMapper;

    public BookResponse create(BookRequest request){
        Book book= bookMapper.toRequest(request);
        Book saved=bookRepository.save(book);
        return bookMapper.toResponse(saved);
    }
    public List<BookResponse> getAllBook(){
        return bookRepository.findAll().stream().map(bookMapper::toResponse).collect(Collectors.toList());
    }
    public BookResponse getById(Long id){
        Book book=bookRepository.findById(id).orElseThrow(()->new RuntimeException("Book not found"));
        return bookMapper.toResponse(book);
    }

    public BookResponse update(Long id,BookRequest request){
        Book existing=bookRepository.findById(id).orElseThrow(()->new RuntimeException("Book not found"));
        existing.setTitle(request.getTitle());
        existing.setStock(request.getStock());
        if(request.getAuthorId()!=null){
            Book updated=bookMapper.toRequest(request);
            existing.setAuthor(updated.getAuthor());
        }
        Book saved=bookRepository.save(existing);
        return bookMapper.toResponse(saved);
    }
    public void delete(Long id){
        if(!bookRepository.existsById(id)){
            throw new RuntimeException("book not found");
        }
        bookRepository.deleteById(id);
    }
    @Transactional
    public void borrowBook(Long memberId,Long bookId){
        Member member=memberRepository.findById(memberId).orElseThrow(()->new RuntimeException("meber nor found"));
        Book book=bookRepository.findById(bookId).orElseThrow(()->new RuntimeException("book not found"));
        if(book.getStock()<1){
            throw new RuntimeException("out of stock");
        }
        book.setStock(book.getStock()-1);
        bookRepository.save(book);

        member.getBorrowedBooks().add(book);
        memberRepository.save(member);
    }


}
