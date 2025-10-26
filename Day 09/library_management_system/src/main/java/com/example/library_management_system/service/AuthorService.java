package com.example.library_management_system.service;

import com.example.library_management_system.dto.AuthorRequest;
import com.example.library_management_system.dto.AuthorResponse;
import com.example.library_management_system.mapper.AuthorMapper;
import com.example.library_management_system.model.Author;
import com.example.library_management_system.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    AuthorMapper authorMapper;

    public AuthorResponse create(AuthorRequest request){
        Author author=authorMapper.toRequest(request);
        return authorMapper.toResponse(authorRepository.save(author));
    }
    public List<AuthorResponse>getAllAuthors(){
        return authorRepository.findAll().stream().map(authorMapper::toResponse).collect(Collectors.toList());
    }
    public void delete(Long id){
        authorRepository.deleteById(id);
    }

}
