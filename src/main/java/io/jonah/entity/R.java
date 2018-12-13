package io.jonah.entity;

import cn.hutool.http.HttpStatus;
import lombok.Data;

/**
 * VO类
 * @author yanjianfei
 *
 */
@Data
public class R {

	private Integer code;
	
	private String msg;
	
	private Object data;
	
	
	
	public static R ok(){
		R r = new R();
		r.code = HttpStatus.HTTP_OK;
		r.msg = "OK";
		return r;
	}
	
	public static R error(String msg){
		R r = new R();
		r.code = HttpStatus.HTTP_BAD_REQUEST;
		r.msg = msg;
		return r;
	}
	
	public static R error(Integer code, String msg){
		R r = new R();
		r.code = code;
		r.msg = msg;
		return r;
	}
	
	
	public R data(Object data) {
		this.data = data;
		return this;
	}
	
}
