package io.github.jaychoufans.cms.common;

import io.github.jaychoufans.cms.utils.WebUtils;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@Accessors(chain = true)
public class ApiResponse<T> {

	private Date timestamp;

	private int status;

	private String error;

	private String message;

	private String path;

	private T data;

	public static ApiResponse<?> ok(Object data) {
		return new ApiResponse<>().setTimestamp(new Date()).setStatus(HttpStatus.OK.value())
				.setError(HttpStatus.OK.getReasonPhrase()).setMessage(HttpStatus.OK.getReasonPhrase())
				.setPath(WebUtils.getRequestURI()).setData(data);
	}

}
