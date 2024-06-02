package com.jtsp.compositeservice.service;


import com.jtsp.compositeservice.dto.PackageRequestDTO;
import com.jtsp.compositeservice.dto.UserPackageRequestDTO;
import com.jtsp.compositeservice.service.remote.RemotePackageService;
import com.jtsp.compositeservice.service.remote.RemoteUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CompositeService {

    private final RemotePackageService packageService;

    private final RemoteUserService userService;

    private static Long ID_COUNTER = 1L;

    @Autowired
    public CompositeService(RemotePackageService packageService, RemoteUserService userService) {
        this.packageService = packageService;
        this.userService = userService;
    }

    public String createUserPackage(UserPackageRequestDTO userPackageRequestDTO) {
        ResponseEntity<String> packageResult = packageService.createPackage(
                PackageRequestDTO.builder()
                        .id(ID_COUNTER++)
                        .name(userPackageRequestDTO.getPackageName())
                        .vendor(userPackageRequestDTO.getPackageVendor())
                        .build()
        );

        log.info("user id {}", userPackageRequestDTO.getUserId());
        ResponseEntity<String> userResult = userService.getUserInfoCopy();
        return "success from package svc: " + packageResult.getBody() + " from user svc :" + userResult.getBody();

    }

    public String createUserPackageCopy(UserPackageRequestDTO userPackageRequestDTO) {
        ResponseEntity<String> packageResult = packageService.createPackage(
                PackageRequestDTO.builder()
                        .id(ID_COUNTER++)
                        .name(userPackageRequestDTO.getPackageName())
                        .vendor(userPackageRequestDTO.getPackageVendor())
                        .build()
        );

        log.info("user id {}", userPackageRequestDTO.getUserId());
        ResponseEntity<String> userResult = userService.getUserInfo(userPackageRequestDTO.getUserId());
        return "success from package svc: " + packageResult.getBody() + " from user svc :" + userResult.getBody();

    }
}
