package io.github.jaychoufans.cms.config;

import io.github.jaychoufans.cms.interceptor.PermissionHandlerInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Value("file:${user.home}/cms/outside/")
	private String outsideResources;

	@Resource
	private PermissionHandlerInterceptor permissionHandlerInterceptor;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 用来动态引入外部资源文件
		registry.addResourceHandler("/outside/**").addResourceLocations(outsideResources);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(permissionHandlerInterceptor).addPathPatterns("/**").excludePathPatterns("/webjars/**",
				"/js/**", "/layuiadmin/**", "/favicon.ico");
	}

}
