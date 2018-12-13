package io.jonah.common.validation;

import java.util.Objects;

import cn.hutool.core.util.StrUtil;

/**
 * 自定义断言
 * @author yanjianfei
 * @date 2018-12-12 16:53:23
 */
public class Assert {

	public static void notBlank(String obj, String msg){
		if(StrUtil.isBlank(obj))
			throw new IllegalArgumentException(msg);
	}
	
	public static void notNull(String obj, String msg){
		if(Objects.isNull(obj))
			throw new IllegalArgumentException(msg);
	}


}
