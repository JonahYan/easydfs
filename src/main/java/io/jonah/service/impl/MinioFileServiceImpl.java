package io.jonah.service.impl;

import java.io.InputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Maps;

import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import io.jonah.common.exception.FileException;
import io.jonah.common.validation.Assert;
import io.jonah.repository.MinioRepository;
import io.jonah.service.IFileService;

@Service
public class MinioFileServiceImpl implements IFileService {
	
	@Resource
	private MinioRepository minioRepository;
	
	private static Map<String, String> contentTypeMap;
	
	private final static String DEFAULT_CONTENT_TYPE = "application/octet-stream";
	
	static {
		contentTypeMap = Maps.newHashMap();
		contentTypeMap.put("doc", "application/msword");
		contentTypeMap.put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		contentTypeMap.put("pdf", "application/pdf");
		contentTypeMap.put("xls", "application/vnd.ms-excel");
		contentTypeMap.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		contentTypeMap.put("ppt", "application/vnd.ms-powerpoint");
		contentTypeMap.put("pptx", "pplication/vnd.openxmlformats-officedocument.presentationml.presentation");
		contentTypeMap.put("rar", "application/x-rar-compressed");
		contentTypeMap.put("gif", "image/gif");
		contentTypeMap.put("jpeg","image/jpeg");
		contentTypeMap.put("jpg", "image/jpeg");
		contentTypeMap.put("png", "image/png");
		contentTypeMap.put("txt", "text/plain");
		contentTypeMap.put("zip", "application/x-zip-compressed");
	}
	
	

	@Override
	public String upload(String bucket, MultipartFile file) throws Exception {
		Assert.notBlank(bucket, "bucket不能为空");
		long size = file.getSize();
		if(size <= 0)
			throw new FileException("上传的文件不能为空");
		if(!minioRepository.checkBucket(bucket))
			throw new FileException(StrUtil.format("该bucket：{} 不存在", bucket));
		// 组成新的文件名
		String fileName = IdUtil.objectId() + StrUtil.DOT + getExt(file.getOriginalFilename());
		minioRepository.put(bucket, fileName, file.getInputStream(), size, file.getContentType());
		return fileName;
	}
	


	
	@Override
	public void download(String bucket, String fileId, HttpServletResponse response) throws Exception{
		Assert.notBlank(bucket, "bucket不能为空");
		Assert.notBlank(fileId, "文件Id不能为空.");
		
		InputStream in = minioRepository.get(bucket, fileId);
		
		// 设置响应头
		String contentType = contentTypeMap.get(getExt(fileId));
        response.setContentType(StrUtil.isNotBlank(contentType) ? contentType : DEFAULT_CONTENT_TYPE);
        
        String fileName = encodeFileName(fileId);
        
        //下载文件能正常显示中文
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        
        FastByteArrayOutputStream buffer = IoUtil.read(in);
		IoUtil.write(response.getOutputStream(), false, buffer.toByteArray());
	}
	
	
	/**
	 * 对中文名称进行编码
	 * @param filename 文件名称
	 * @return
	 */
	private String encodeFileName(String filename){
		return URLUtil.encode(filename);
	}
	
	/**
	 * 得到文件后缀名
	 * @param filename 文件完整名称
	 * @return 文件后缀
	 */
	private String getExt(String filename){
		String extName = StrUtil.subAfter(filename, '.', true);
		return StrUtil.isNotBlank(extName) ? extName : ""; 
	}
}
