package com.fgbg.common.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix ="minio")
@Data
public class MinioConfig {
    private String Endpoint;
    private String AccessKey;
    private String SecretKey;
    private String BucketName;
}
