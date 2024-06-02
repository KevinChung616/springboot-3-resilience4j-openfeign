package com.jtsp.compositeservice.service.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE", configuration = FeignClientProperties.FeignClientConfiguration.class)
public interface RemoteUserService {

    @GetMapping("/users/{id}")
    ResponseEntity<String> getUserInfo(@PathVariable("id") Long id);

    @GetMapping("/users")
    public ResponseEntity<String> getUserInfoCopy();


}
