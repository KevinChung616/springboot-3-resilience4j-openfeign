package com.jtsp.compositeservice.service.remote;

import com.jtsp.compositeservice.dto.PackageRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("PACKAGE-SERVICE")
public interface RemotePackageService {

    @PostMapping("/packages")
    ResponseEntity<String> createPackage(@RequestBody PackageRequestDTO requestDTO);
}
