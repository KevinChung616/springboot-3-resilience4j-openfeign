package com.jtsp.compositeservice.dto;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserPackageRequestDTO {
    private Long userId;
    private String packageName;
    private String packageVendor;
}
