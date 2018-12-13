package io.jonah.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "easydfs.minio")
public class MinioProperties {

	private String url;
	
	private String accessKey;
	
	private String secretAccessKey;
	
	private Integer connectTimeout;
	
	private Integer writeTimeout;
	
	private Integer readTimeout;
	
}
