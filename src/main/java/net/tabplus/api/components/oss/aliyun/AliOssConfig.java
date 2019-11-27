package net.tabplus.api.components.oss.aliyun;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliOssConfig {
    public static String accessId;
    public static String accessKey;
    public static String endpoint;
    public static String bucket;
    public static String host;
    public static String cdnHost;
    public static String callbackUrl;
    public static String dirPrefix;

    public void setAccessId(String accessId) {
        AliOssConfig.accessId = accessId;
    }

    public void setAccessKey(String accessKey) {
        AliOssConfig.accessKey = accessKey;
    }

    public void setEndpoint(String endpoint) {
        AliOssConfig.endpoint = endpoint;
    }

    public void setBucket(String bucket) {
        AliOssConfig.bucket = bucket;
    }

    public void setHost(String host) {
        AliOssConfig.host = host;
    }

    public void setCdnHost(String cdnHost) {
        AliOssConfig.cdnHost = cdnHost;
    }

    public void setCallbackUrl(String callbackUrl) {
        AliOssConfig.callbackUrl = callbackUrl;
    }

    public void setDirPrefix(String dirPrefix) {
        AliOssConfig.dirPrefix = dirPrefix;
    }
}
