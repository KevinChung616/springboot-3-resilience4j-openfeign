package com.jtsp.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public ResponseEntity<String> getUserInfo(@PathVariable Long id) {
        return ResponseEntity.ok("user name is John with id : " + id);
    }

    @GetMapping
    public ResponseEntity<String> getUserInfoCopy() {
        return ResponseEntity.ok("copy user name is John with id empty");
    }

}
