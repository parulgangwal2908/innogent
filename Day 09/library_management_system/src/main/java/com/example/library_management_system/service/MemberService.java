package com.example.library_management_system.service;

import com.example.library_management_system.dto.MemberRequest;
import com.example.library_management_system.dto.MemberResponse;
import com.example.library_management_system.mapper.MemberMapper;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.model.Member;
import com.example.library_management_system.repository.BookRepository;
import com.example.library_management_system.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    MemberMapper memberMapper;

    public MemberResponse create(MemberRequest request){
        Member member = memberMapper.toRequest(request);

        if (request.getBorrowedBookIds() != null && !request.getBorrowedBookIds().isEmpty()) {
            Set<Book> books = request.getBorrowedBookIds().stream()
                    .map(id -> bookRepository.findById(id).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());

            member.setBorrowedBooks(books);
        }

        Member savedMember = memberRepository.save(member);
        return memberMapper.toResponse(savedMember);
    }
    public List<MemberResponse>getAll(){
        return memberRepository.findAll().stream().map(memberMapper::toResponse).collect(Collectors.toList());
    }
    public MemberResponse update(Long id,MemberRequest request){
        Member existing =memberRepository.findById(id).orElseThrow(()->new RuntimeException("member not found"));
        existing.setName(request.getName());
        Member update=memberRepository.save(existing);
        return memberMapper.toResponse(update);
    }
    public void deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new RuntimeException("Member not found");
        }
        memberRepository.deleteById(id);
    }

}
