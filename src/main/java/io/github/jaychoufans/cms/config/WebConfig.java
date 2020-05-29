package io.github.jaychoufans.cms.config;

import io.github.jaychoufans.cms.interceptor.ShiroAnnotationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ShiroAnnotationInterceptor()).addPathPatterns("/**")
				.excludePathPatterns("/webjars/**", "/js/**", "/layuiadmin/**", "/favicon.ico");
	}

}
