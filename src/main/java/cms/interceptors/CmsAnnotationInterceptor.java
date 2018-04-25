package cms.interceptors;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cms.annotation.ContainCms;
import cms.pojo.TemplatePageInstancePublish;
import cms.service.TemplatePageInstancePublishService;

@Component
public class CmsAnnotationInterceptor implements HandlerInterceptor {

	private TemplatePageInstancePublishService templatePageInstancePublishService;

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse respsonse, Object handler, ModelAndView modelAndView) throws Exception {
		HandlerMethod methodHandler = (HandlerMethod) handler;
		System.out.println("注解拦截");
		ContainCms containsCms = methodHandler.getMethodAnnotation(ContainCms.class);
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		templatePageInstancePublishService = (TemplatePageInstancePublishService) webApplicationContext.getBean(TemplatePageInstancePublishService.class);
		// 如果方法中添加了@ContainsCms，自动填充CMS数据
		if (null != containsCms) {
			String path = request.getRequestURI();
			TemplatePageInstancePublish templatePageInstancePublish = templatePageInstancePublishService.getCurrentTemplatePageInstancePublishData(path,
					new Date());
			String jsonData;
			if (null == templatePageInstancePublish) {
				jsonData = "";
			} else {
				jsonData = templatePageInstancePublish.getValueCn();
			}
			if (StringUtils.isNoneEmpty(jsonData)) {
				modelAndView.addObject("jsonData", jsonData);
			}
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		System.out.println("cms拦截器");
		return true;
	}

}
