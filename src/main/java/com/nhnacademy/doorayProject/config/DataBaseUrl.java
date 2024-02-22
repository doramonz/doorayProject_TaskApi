package com.nhnacademy.doorayProject.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "com.nhnacademy.account")
public class DataBaseUrl {
    private String address;
}
