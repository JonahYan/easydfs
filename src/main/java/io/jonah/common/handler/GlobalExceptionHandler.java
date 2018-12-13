package io.jonah.common.handler;

import java.io.IOException;

import org.apache.catalina.connector.ClientAbortException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import io.jonah.common.exception.FileException;
import io.jonah.entity.R;
import io.minio.errors.MinioException;
import lombok.extern.slf4j.Slf4j;

/**
 * 全局Controller层异常处理
 * @author yanjianfei
 * @date 2018-12-12 18:23:01
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	 @ExceptionHandler(Exception.class)
	 public R error(Exception e){
		 if(e instanceof NoHandlerFoundException)
			 return R.error(HttpStatus.HTTP_NOT_FOUND, "路径不存在，请检查路径是否正确");
		 else if(e instanceof NoHandlerFoundException)
			 return R.error(HttpStatus.HTTP_NOT_FOUND, "路径不存在，请检查路径是否正确");
		 else if(e instanceof FileException || e instanceof IllegalArgumentException
				 || e instanceof MinioException){ //业务异常
			 return R.error(e.getMessage());
		 }else if(e instanceof HttpMessageNotReadableException || e instanceof MissingServletRequestParameterException)
			 return R.error("参数转换异常，请联系管理员！");
		 else if(e instanceof HttpRequestMethodNotSupportedException)
			 return R.error("请求的方法不支持：["+((HttpRequestMethodNotSupportedException)e).getMethod()+"]方式！");
		 else if(e instanceof MultipartException)
			 return R.error("上传文件失败请检查上传的文件是否超出大小或者上传的文件是否存在");
		 else if(e instanceof IORuntimeException || e instanceof IOException
				 || e instanceof ClientAbortException)
			 return R.error(StrUtil.format("客户端或服务端有一方已经关闭了链接：{}", e.getMessage()));
		 else if(e instanceof HttpMediaTypeNotAcceptableException)
			 return R.error(StrUtil.format("失败原因：{}", e.getMessage()));
		 
		log.error(e.getMessage(), e);
        return R.error(HttpStatus.HTTP_INTERNAL_ERROR, e.getMessage());
	 }
}
