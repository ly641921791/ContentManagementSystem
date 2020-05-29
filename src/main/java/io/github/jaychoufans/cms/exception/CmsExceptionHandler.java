package io.github.jaychoufans.cms.exception;

import io.github.jaychoufans.cms.common.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CmsExceptionHandler {

	@ExceptionHandler(CmsException.class)
	public ApiResponse<?> cmsException(CmsException exception) {
		return ApiResponse.error(exception.getError(), exception.getMessage());
	}

}
