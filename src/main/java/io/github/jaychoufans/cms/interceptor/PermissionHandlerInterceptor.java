package io.github.jaychoufans.cms.interceptor;

import io.github.jaychoufans.cms.annotation.RequiresPermission;
import io.github.jaychoufans.cms.common.Const;
import io.github.jaychoufans.cms.exception.CmsException;
import io.github.jaychoufans.cms.model.SystemConfig;
import io.github.jaychoufans.cms.model.SystemPermission;
import io.github.jaychoufans.cms.model.SystemUser;
import io.github.jaychoufans.cms.service.SystemConfigService;
import io.github.jaychoufans.cms.service.SystemUserService;
import io.github.jaychoufans.cms.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PermissionHandlerInterceptor
		implements HandlerInterceptor, ApplicationListener<WebServerInitializedEvent> {

	private HashMap<Method, String> permissionMap;

	@Resource
	private SystemUserService systemUserService;

	@Resource
	private SystemConfigService systemConfigService;

	@Override
	public void onApplicationEvent(WebServerInitializedEvent webServerInitializedEvent) {
		permissionMap = new HashMap<>();
		RequestMappingHandlerMapping handlerMapping = webServerInitializedEvent.getApplicationContext()
				.getBean(RequestMappingHandlerMapping.class);
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
		handlerMethods.forEach(((requestMappingInfo, handlerMethod) -> permissionMap.put(handlerMethod.getMethod(),
				requestMappingInfo.toString())));
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (handler instanceof ResourceHttpRequestHandler) {
			return true;
		}

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		if (method.isAnnotationPresent(RequiresPermission.class)) {
			SystemUser user = WebUtils.getCurrentUser();
			if (user == null) {
				SystemConfig visitorStatus = systemConfigService.getByKey(Const.visitorStatus);
				// 支持游客功能
				if (visitorStatus != null && Const.visitorStatusEnable.equals(visitorStatus.getConfigValue())) {
					WebUtils.setCurrentUser(systemUserService.getById(0));
				}
				else {
					throw new CmsException("A0230", "用户登陆已过期");
				}
			}

			// 获得 requestMapping 当前的请求地址，查询是否包含在该用户的权限列表中
			String permissionCode = permissionMap.get(method);
			Set<String> permissionCodes = systemUserService.getPermissionById(user.getId()).stream()
					.map(SystemPermission::getUrl).collect(Collectors.toSet());
			if (!permissionCodes.contains(permissionCode)) {
				if (Const.visitorUserId.equals(user.getId())) {
					throw new CmsException("A0312", "游客无权限使用该 API ，选择右上角当前用户，点击登入按钮，进入登录页面登录后再次访问");
				}
				else {
					throw new CmsException("A0312", "无权限使用 API");
				}
			}

		}

		return true;
	}

}
