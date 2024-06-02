package com.jtsp.compositeservice.controller;

import com.jtsp.compositeservice.dto.UserPackageRequestDTO;
import com.jtsp.compositeservice.service.CompositeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/composite")
public class CompositeController {

    private final CompositeService compositeService;

    @Autowired
    public CompositeController(CompositeService compositeService) {
        this.compositeService = compositeService;
    }

    @PostMapping("/user-package")
    public ResponseEntity<String> createPackageForUser(@RequestBody UserPackageRequestDTO requestDTO) {
        return ResponseEntity.ok(compositeService.createUserPackage(requestDTO));
    }

    @PostMapping("/user-package-copy")
    public ResponseEntity<String> createPackageForUserCopy(@RequestBody UserPackageRequestDTO requestDTO) {
        return ResponseEntity.ok(compositeService.createUserPackageCopy(requestDTO));
    }
}
