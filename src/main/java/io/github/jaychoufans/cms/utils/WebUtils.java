package io.github.jaychoufans.cms.utils;

import io.github.jaychoufans.cms.model.SystemUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {

	public static void setCurrentUser(SystemUser user) {
		getRequest().getSession().setAttribute("user", user);
	}

	public static SystemUser getCurrentUser() {
		return (SystemUser) getRequest().getSession().getAttribute("user");
	}

	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	}

	public static String getRequestURI() {
		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
				.getRequestURI();
	}

}
