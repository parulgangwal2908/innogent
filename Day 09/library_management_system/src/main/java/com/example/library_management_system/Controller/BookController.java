package com.example.library_management_system.Controller;

import com.example.library_management_system.dto.BookRequest;
import com.example.library_management_system.dto.BookResponse;
import com.example.library_management_system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;
    @PostMapping
    public ResponseEntity<BookResponse> create(@RequestBody BookRequest request){
        return ResponseEntity.ok(bookService.create(request));
    }
    @GetMapping
    public ResponseEntity<List<BookResponse>> get(){
        return ResponseEntity.ok(bookService.getAllBook());
    }
    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> update(@PathVariable Long id,@RequestBody BookRequest request){
        return ResponseEntity.ok(bookService.update(id,request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        bookService.delete(id);
        return ResponseEntity.ok("Book deleted");
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse>getById(@PathVariable Long id){
        return ResponseEntity.ok(bookService.getById(id));
    }
    @PostMapping("/borrow")
    public ResponseEntity<String> borrowBook(@RequestParam Long memberId,@RequestParam Long bookId){
        try{
            bookService.borrowBook(memberId,bookId);
            return ResponseEntity.ok("Book borrowed Successfully");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
