package cms.spring.conifg;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cms.interceptors.CmsAnnotationInterceptor;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	/**
	 * 添加拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CmsAnnotationInterceptor()).addPathPatterns("/cms/*");
		super.addInterceptors(registry);
	}
}
