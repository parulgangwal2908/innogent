package com.example.library_management_system.Controller;

import com.example.library_management_system.dto.AuthorRequest;
import com.example.library_management_system.dto.AuthorResponse;
import com.example.library_management_system.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")

public class AuthorController {
    @Autowired
    AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorResponse> create (@RequestBody AuthorRequest request){
        return ResponseEntity.ok(authorService.create(request));

    }
    @GetMapping
    public ResponseEntity<List<AuthorResponse>>get(){
        return ResponseEntity.ok(authorService.getAllAuthors());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        authorService.delete(id);
        return ResponseEntity.ok("Author deleted");
    }
}
