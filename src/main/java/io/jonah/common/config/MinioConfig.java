package io.jonah.common.config;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jonah.entity.MinioProperties;
import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;

@Configuration
@ConditionalOnClass(MinioClient.class)
@EnableConfigurationProperties(MinioProperties.class)
public class MinioConfig {
	
	@Value("${easydfs.minio.buckets}")
	private String[] buckets;
	
	
	@Bean
	public MinioClient initMinioClient(@Autowired MinioProperties minioProperties) throws InvalidEndpointException, InvalidPortException {
		 MinioClient minioClient = new MinioClient(minioProperties.getUrl(), minioProperties.getAccessKey(), minioProperties.getSecretAccessKey());
		 minioClient.setTimeout(TimeUnit.SECONDS.toMillis(minioProperties.getConnectTimeout()), 
				 TimeUnit.SECONDS.toMillis(minioProperties.getWriteTimeout()),
				 TimeUnit.SECONDS.toMillis(minioProperties.getReadTimeout()));
		 //检查bucket是否存在，不存在就创建
		 checkBucket(minioClient);
		return minioClient;
	}
	
	
	private void checkBucket(MinioClient minioClient){
		Stream.of(buckets).forEach(bucket ->{
			try {
				if(minioClient.bucketExists(bucket)) return;
				minioClient.makeBucket(bucket);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

}
