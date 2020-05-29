package io.github.jaychoufans.cms.interceptor;

import io.github.jaychoufans.cms.exception.CmsException;
import io.github.jaychoufans.cms.utils.WebUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class ShiroAnnotationInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		if (method.isAnnotationPresent(RequiresUser.class)) {
			Object user = WebUtils.getRequest().getSession().getAttribute("user");
			if (user == null) {
				throw new CmsException("A0230", "用户登陆已过期");
			}
		}
		return true;
	}

}
