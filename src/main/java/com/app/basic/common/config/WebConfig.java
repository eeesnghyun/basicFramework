package com.app.basic.common.config;

import com.app.basic.common.interceptor.CustomInterceptor;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

   	private final CustomInterceptor customInterceptor;

    @Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(customInterceptor)
				//Interceptor가 적용될 경로
				.addPathPatterns("/**")
				//제외 경로
				.excludePathPatterns("/resources/**");
	}

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/static/**")
                .addResourceLocations("classpath:/resources/static/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
		registry.addResourceHandler("/resources/**")
                .addResourceLocations("classpath:/static/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));

    }
}
