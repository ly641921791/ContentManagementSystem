package io.github.jaychoufans.cms.exception;

import lombok.Getter;
import lombok.ToString;

@ToString
public class CmsException extends RuntimeException {

	@Getter
	private final String error;

	public CmsException(String error, String message) {
		super(message);
		this.error = error;
	}

}
