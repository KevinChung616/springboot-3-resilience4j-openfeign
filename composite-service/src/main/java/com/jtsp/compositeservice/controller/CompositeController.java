package com.jtsp.compositeservice.controller;

import com.jtsp.compositeservice.dto.UserPackageRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/composite")
public class CompositeController {

    @GetMapping("/package")
    public ResponseEntity<String> createPackageForUser(@RequestBody UserPackageRequestDTO requestDTO) {
        return ResponseEntity.ok("create success");
    }
}
