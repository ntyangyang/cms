package cms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cms.annotation.ContainCms;
import cms.constants.CmsConstants;
import cms.pojo.CmsTemplate;
import cms.pojo.TemplatePageInstance;
import cms.service.TemplatePageInstanceService;
import cms.service.TemplateService;

@RequestMapping("/cms/data")
@Controller
public class CmsDispatcherController {

	@Autowired
	private TemplatePageInstanceService templatePageInstanceService;

	@Autowired
	private TemplateService templateService;

	@RequestMapping(value = { "/preview" })
	public String preview(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			String previewParam = request.getParameter("previewParam");
			String jsonData = "";
			Long instanceId = Long.valueOf(previewParam);
			TemplatePageInstance templatePageInstance = templatePageInstanceService.findTemplatePageInstanceById(instanceId);
			CmsTemplate cmsTemplate = templateService.findTemplateById(templatePageInstance.getTemplateId());
			if (null != templatePageInstance) {
				jsonData = templatePageInstance.getValueCn();
			}

			model.addAttribute("jsonData", jsonData);
			switch (cmsTemplate.getType()) {
			case CmsConstants.HOMEPAGE:
				return "/cms/homepage";
			case CmsConstants.CATEGORY:
				return "/cms/category";
			case CmsConstants.LOOKS:
				return "/cms/looks";
			case CmsConstants.SHOWS:
				return "/cms/shows";
			default:
				return "/cms/homepage";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "/errors/404";
		}
	}

	@RequestMapping(value = { "/preview/{html}" })
	@ContainCms
	public String preview(@PathVariable("html") String html, HttpServletRequest request, HttpServletResponse response, Model model) {
		return "/cms/html";
	}
}
