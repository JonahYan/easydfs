package io.jonah;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.junit.Test;

import io.minio.MinioClient;

public class TestMinio {

	
	@Test
	public void testClient(){
		 try {
		      // 使用Minio服务的URL，端口，Access key和Secret key创建一个MinioClient对象
		      MinioClient minioClient = new MinioClient("http://172.16.0.68:9000", "BJ1XTH099QIICK06Z6ZA", "8wzLEflTWAnfirMk7WxmCHLuNXfZiFj+eb4OSt6h");
		      // 检查存储桶是否已经存在
		      boolean isExist = minioClient.bucketExists("test");
		      if(isExist) {
		    	  System.out.println("Bucket already exists.");
		      } else {
		        // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
		        minioClient.makeBucket("test");
		      }

		      // 使用putObject上传一个文件到存储桶中。
		      minioClient.putObject("test", "face.jpg", "D://face.jpg");
		    } catch(Exception e) {
		      System.out.println("Error occurred: " + e);
		    }
		
	}
	
	
	
	@Test
	public void test2(){
		 try {
		      // 使用Minio服务的URL，端口，Access key和Secret key创建一个MinioClient对象
		      MinioClient minioClient = new MinioClient("http://172.16.0.140:9000", "BJ1XTH099QIICK06Z6ZA", "8wzLEflTWAnfirMk7WxmCHLuNXfZiFj+eb4OSt6h");
		      // 检查存储桶是否已经存在
		      boolean isExist = minioClient.bucketExists("test");
		      if(isExist) {
		    	  System.out.println("Bucket already exists.");
		      } else {
		        // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
		        minioClient.makeBucket("test");
		      }

		      InputStream in = minioClient.getObject("test", "face.jpg");
		      
		      BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
		      FileOutputStream out = new FileOutputStream(new File("D://test.jpg"));
		      BufferedOutputStream outputStream = new BufferedOutputStream(out);
		      
		      byte[] b = new byte[1024];
		      int len = -1;
		      while((len = bufferedInputStream.read(b)) != -1){
		    	  outputStream.write(b, 0, len);
		      }
		      outputStream.flush();
		      outputStream.close();
		      out.close();
		      bufferedInputStream.close();
		      in.close();
		    } catch(Exception e) {
		      System.out.println("Error occurred: " + e);
		    }
		
	}
	
	
	@Test
	public void test3(){
		 try {
		      // 使用Minio服务的URL，端口，Access key和Secret key创建一个MinioClient对象
		      MinioClient minioClient = new MinioClient("http://127.0.0.1:9000", "BJ1XTH099QIICK06Z6ZA", "8wzLEflTWAnfirMk7WxmCHLuNXfZiFj+eb4OSt6h");
		      // 检查存储桶是否已经存在
		      boolean isExist = minioClient.bucketExists("test");
		      if(isExist) {
		    	  System.out.println("Bucket already exists.");
		      } else {
		        // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
		        minioClient.makeBucket("test");
		      }

		      System.out.println(minioClient.getObjectUrl("test", "face.jpg"));
		    } catch(Exception e) {
		      System.out.println("Error occurred: " + e);
		    }
		
	}
	
}
