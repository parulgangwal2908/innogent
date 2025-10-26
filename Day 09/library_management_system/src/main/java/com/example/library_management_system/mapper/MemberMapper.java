package com.example.library_management_system.mapper;

import com.example.library_management_system.dto.MemberRequest;
import com.example.library_management_system.dto.MemberResponse;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.model.Member;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MemberMapper {
    public Member toRequest(MemberRequest request){
        Member member=new Member();
        member.setName(request.getName());


        return member;
    }
    public MemberResponse toResponse(Member member){
        MemberResponse response=new MemberResponse();
        response.setName(member.getName());
        response.setId(member.getId());
        if(member.getBorrowedBooks()!=null){
            response.setBorrowedBooks(member.getBorrowedBooks().stream().map(book->book.getTitle()).collect(Collectors.toSet()));
        }
        return response;
    }
}
