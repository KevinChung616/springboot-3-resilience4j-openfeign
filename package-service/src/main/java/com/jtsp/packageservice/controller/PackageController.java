package com.jtsp.packageservice.controller;

import com.jtsp.packageservice.dto.PackageRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/packages")
public class PackageController {
    @PostMapping
    public ResponseEntity<String> createPackage(@RequestBody PackageRequestDTO dto) {
        return ResponseEntity.ok("create package success + " + dto.getName());
    }
}
