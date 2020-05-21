package io.github.jaychoufans.cms.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.jaychoufans.cms.utils.WebUtils;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.util.Collections;

@Data
@Accessors(chain = true)
public class ApiResponse<T> {

	private long timestamp;

	private int status;

	private String error;

	private String message;

	private String path;

	private long total = 1;

	private T data;

	public static ApiResponse<?> ok(Object data) {
		return new ApiResponse<>().setTimestamp(System.currentTimeMillis()).setStatus(HttpStatus.OK.value())
				.setError("00000").setMessage(HttpStatus.OK.getReasonPhrase()).setPath(WebUtils.getRequestURI())
				.setData(data);
	}

	public static ApiResponse<?> ok(Page<?> page) {
		return new ApiResponse<>().setTimestamp(System.currentTimeMillis()).setStatus(HttpStatus.OK.value())
				.setError("00000").setMessage(HttpStatus.OK.getReasonPhrase()).setPath(WebUtils.getRequestURI())
				.setTotal(page.getTotal()).setData(page.getRecords());
	}

	public static ApiResponse<?> error(String error, String message) {
		return new ApiResponse<>().setTimestamp(System.currentTimeMillis()).setStatus(HttpStatus.OK.value())
				.setError(error).setMessage(message).setPath(WebUtils.getRequestURI()).setData(Collections.EMPTY_MAP);
	}

}
