package com.jtsp.packageservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageRequestDTO {
    private long id;
    private String name;
    private String vendor;
}
