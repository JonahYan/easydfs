package io.jonah.common.exception;

/**
 * 自定义异常
 * @author yanjianfei
 * @date 2018-12-12 15:46:39
 */
public class FileException extends RuntimeException {

	private static final long serialVersionUID = 1631954587229707632L;

	public FileException() {}

	/**
	 * @param message
	 */
	public FileException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public FileException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public FileException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public FileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
