package io.github.jaychoufans.cms.config;

import io.github.jaychoufans.cms.interceptor.PermissionHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Resource
	private PermissionHandlerInterceptor permissionHandlerInterceptor;

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(permissionHandlerInterceptor).addPathPatterns("/**").excludePathPatterns("/webjars/**",
				"/js/**", "/layuiadmin/**", "/favicon.ico");
	}

}
