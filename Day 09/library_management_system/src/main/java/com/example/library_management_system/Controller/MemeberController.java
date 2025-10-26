package com.example.library_management_system.Controller;

import com.example.library_management_system.dto.MemberRequest;
import com.example.library_management_system.dto.MemberResponse;
import com.example.library_management_system.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemeberController {
    @Autowired
    MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponse> create(@RequestBody MemberRequest request){
        return ResponseEntity.ok(memberService.create(request));
    }
    @GetMapping
    public ResponseEntity<List<MemberResponse>>getAll(){
        return ResponseEntity.ok(memberService.getAll());
    }
    @PutMapping("/{id}")
    public ResponseEntity<MemberResponse> update(@PathVariable Long id,@RequestBody MemberRequest request){
        return ResponseEntity.ok(memberService.update(id,request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable Long id){
        memberService.deleteMember(id);
        return ResponseEntity.ok("Member deleted ");
    }


}
