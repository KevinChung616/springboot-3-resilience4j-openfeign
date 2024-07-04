package com.jtsp.compositeservice.service.remote;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE", configuration = FeignClientProperties.FeignClientConfiguration.class)
public interface RemoteUserService {

    @GetMapping("/users/{id}")
//    @CircuitBreaker(name = "user", fallbackMethod = "fallbackMethod")
    @Retry(name = "default", fallbackMethod = "fallbackMethodRetry")
    ResponseEntity<String> getUserInfo(@PathVariable("id") Long id);

    @GetMapping("/users")
    @CircuitBreaker(name = "user", fallbackMethod = "fallbackMethod")
    public ResponseEntity<String> getUserCircuit();

    @GetMapping("/users/timeout")
    public ResponseEntity<String> getUserTimeout();

    @GetMapping("/users/within-timeout")
    public ResponseEntity<String> getUserWithinTimeout();

    default ResponseEntity<String> fallbackMethod(Throwable t) {
        return ResponseEntity.ok("default user info from circuit breaker fallback");
    }

    default ResponseEntity<String> fallbackMethodRetry(Throwable t) {
        return ResponseEntity.ok("default user info from retry fallback");
    }




}
