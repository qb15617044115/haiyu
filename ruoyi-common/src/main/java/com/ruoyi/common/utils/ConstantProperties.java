package com.ruoyi.common.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "oss")
public class ConstantProperties {
    //@Value("${oss.endpoint}")
    private  String endpoint;
    private  String keyid;
    private  String keysecret;
    private  String bucketname;
    private  String filehost;
    private  String imgurl;
}
