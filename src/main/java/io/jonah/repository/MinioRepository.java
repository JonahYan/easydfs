package io.jonah.repository;

import java.io.InputStream;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.hutool.core.util.StrUtil;
import io.minio.MinioClient;
import io.minio.errors.MinioException;

/**
 * minio文件持久层
 * @author yanjianfei
 * @date 2018-12-12 14:45:50
 *
 */
@Repository
public class MinioRepository {
	
	@Resource
	private MinioClient minioClient;
	
	
	public void put(String bucket, String fileName, InputStream stream, long size, String contentType) throws MinioException{
		try {
			minioClient.putObject(bucket, fileName, stream, size, contentType);
		}catch (Exception e) {
			throw new MinioException(StrUtil.format("上传文件失败， 失败原因：{}", e.getMessage()));
		}
	}
	
	
	public InputStream get(String bucketName, String fileName) throws MinioException{
		try {
			return minioClient.getObject(bucketName, fileName);	
		}catch (Exception e) {
			throw new MinioException(StrUtil.format("获取文件失败， 失败原因：{}", e.getMessage()));
		}
	}


	public void delete(String bucketName, String fileName) throws MinioException{
		try {
			minioClient.removeObject(bucketName, fileName);
		}catch (Exception e) {
			throw new MinioException(StrUtil.format("删除文件失败， 失败原因：{}", e.getMessage()));
		}
	}
	
	
	public boolean checkBucket(String bucket) throws MinioException{
		try {
			return minioClient.bucketExists(bucket);
		}catch (Exception e) {
			throw new MinioException(StrUtil.format("检查bucket失败， 失败原因：{}", e.getMessage()));
		}
	}

}
