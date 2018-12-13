package io.jonah.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

/**
 * 抽象文件服务接口
 * @author yanjianfei
 * @date 2018-12-12 14:43:42
 *
 */
public interface IFileService {
	
	/**
	 * 上传文件
	 * @param bucket 文件夹名称
	 * @param file 文件
	 * @return 上传成功的自定义文件名称
	 * @throws Exception
	 */
	public String upload(String bucket, MultipartFile file)throws Exception;

	
	/**
	 * 下载文件
	 * @param bucket 文件夹名称
	 * @param fileId 文件id
	 * @param response Http响应 
	 * @throws Exception 
	 */
	public void download(String bucket, String fileId, HttpServletResponse response) throws Exception;

}
