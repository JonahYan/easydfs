package io.jonah.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.jonah.entity.R;
import io.jonah.service.IFileService;

/**
 * 文件服务 前端控制类
 * @author yanjianfei
 * @date 2018-12-12 14:42:28
 */
@RestController
@RequestMapping("file")
public class FileController{
	
	@Resource
	private IFileService fileService;
	
	
	/**
	 * 上传文件
	 * @param bucket 文件夹名称
	 * @param file  文件
	 * @return 自定的文件名
	 * @throws Exception
	 */
	@PostMapping("upload")
	public R upload(String bucket, MultipartFile file) throws Exception{
		return R.ok().data(fileService.upload(bucket, file));
	}
	
	
	/**
	 * 下载文件
	 * @param bucket 文件夹名称
	 * @param fileId 文件id
	 * @param response Http响应对象
	 * @throws Exception
	 */
	@RequestMapping("download/{bucket}/{fileId}")
	public void download(@PathVariable String bucket, @PathVariable String fileId,
			                HttpServletResponse response) throws Exception{
		fileService.download(bucket, fileId, response);
	}

}
