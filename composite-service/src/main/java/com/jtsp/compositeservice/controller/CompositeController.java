package com.jtsp.compositeservice.controller;

import com.jtsp.compositeservice.dto.UserPackageRequestDTO;
import com.jtsp.compositeservice.service.CompositeService;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/composite")
@Slf4j
public class CompositeController {

    private final CompositeService compositeService;

    @Autowired
    private BulkheadRegistry bulkheadRegistry;


    @Autowired
    public CompositeController(CompositeService compositeService) {
        this.compositeService = compositeService;
    }

    @PostMapping("/user-package-circuit")
    public ResponseEntity<String> createPackageForUser(@RequestBody UserPackageRequestDTO requestDTO) {
        return ResponseEntity.ok(compositeService.createUserPackage(requestDTO));
    }

    @PostMapping("/user-package-retry")
    public ResponseEntity<String> createPackageForUserCopy(@RequestBody UserPackageRequestDTO requestDTO) {
        return ResponseEntity.ok(compositeService.createUserPackageRetry(requestDTO));
    }

    @GetMapping("/user-timeout")
    public ResponseEntity<String> userTimeout() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(compositeService.timeout().get());
    }


    @GetMapping("/user-within-timeout")
    public ResponseEntity<String> userWithinTimeout() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(compositeService.withinTimeout().get());
    }

    @GetMapping("/rate-limiter")
    @RateLimiter(name = "default")
    public ResponseEntity<String> rateLimiter() {
        log.info("execute method inside rate limiter controller");
        return ResponseEntity.ok("rate-limiter call");
    }

    @GetMapping("/bulkhead")
    @Bulkhead(name = "default")
    public ResponseEntity<String> bulkhead() throws InterruptedException {
        io.github.resilience4j.bulkhead.Bulkhead bulkhead = bulkheadRegistry.bulkhead("default");
        int available = bulkhead.getMetrics().getAvailableConcurrentCalls();
        int max = bulkhead.getBulkheadConfig().getMaxConcurrentCalls();
        log.info("Bulkhead state - Available calls: {} / {}", available, max);
        log.info("perform bulkhead code");
        return ResponseEntity.ok("bulkhead call");
    }
}
