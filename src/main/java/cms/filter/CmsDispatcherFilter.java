package cms.filter;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cms.pojo.TemplatePageInstancePublish;
import cms.service.TemplatePageInstancePublishService;

@WebFilter(filterName = "firstFilter", urlPatterns = { "/cms/*" })
public class CmsDispatcherFilter implements Filter {

	private WebApplicationContext webApplicationContext;

	private TemplatePageInstancePublishService templatePageInstancePublishService;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		String path = httpServletRequest.getRequestURI();
		List<String> urlList = templatePageInstancePublishService.getTemplatePageInstancePublishUrls();

		if (null != urlList && urlList.contains(path)) {
			request.setAttribute("instanceUrl", path);
			TemplatePageInstancePublish templatePageInstancePublish = templatePageInstancePublishService.getCurrentTemplatePageInstancePublishData(path,
					new Date());
			// 可以做URL分类判断
			if (null != templatePageInstancePublish) {

			}
			chain.doFilter(httpServletRequest, httpServletResponse);
		} else {
			chain.doFilter(httpServletRequest, httpServletResponse);
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext servletContext = filterConfig.getServletContext();
		webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		templatePageInstancePublishService = (TemplatePageInstancePublishService) webApplicationContext.getBean(TemplatePageInstancePublishService.class);

	}

}
