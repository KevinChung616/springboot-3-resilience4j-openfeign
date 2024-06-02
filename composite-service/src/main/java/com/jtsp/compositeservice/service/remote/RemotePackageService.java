package com.jtsp.compositeservice.service.remote;

import com.jtsp.compositeservice.dto.PackageRequestDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("PACKAGE-SERVICE")
public interface RemotePackageService {

    @PostMapping("/packages")
    @CircuitBreaker(name = "package", fallbackMethod = "fallbackMethod")
    ResponseEntity<String> createPackage(@RequestBody PackageRequestDTO requestDTO);

    default ResponseEntity<String> fallbackMethod(Throwable throwable) {
        return ResponseEntity.ok("default package");
    }
}
