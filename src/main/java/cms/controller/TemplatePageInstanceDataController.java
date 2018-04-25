package cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cms.pojo.CmsTemplate;
import cms.pojo.Component;
import cms.pojo.TemplatePageInstance;
import cms.service.ComponentService;
import cms.service.TemplatePageInstanceService;
import cms.service.TemplateService;

@RequestMapping("/cms/instance/data")
@Controller
public class TemplatePageInstanceDataController {

	@Autowired
	private TemplatePageInstanceService templatePageInstanceService;

	@Autowired
	private TemplateService templateService;

	@Autowired
	private ComponentService componentService;

	@RequestMapping("/edit.htm")
	public String goToInstanceDataEditPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			Long instanceId = Long.valueOf(request.getParameter("instanceId"));
			TemplatePageInstance templatePageInstance = templatePageInstanceService.findTemplatePageInstanceById(instanceId);
			Long templateId = templatePageInstance.getTemplateId();
			CmsTemplate cmsTemplate = templateService.findTemplateById(templateId);
			List<Component> componentList = componentService.findAllComponentsByTemplateId(cmsTemplate.getId());
			String value = "";
			value = templatePageInstance.getValueCn();
			model.addAttribute("instanceValue", value);
			model.addAttribute("instanceId", instanceId);
			model.addAttribute("template", cmsTemplate);
			model.addAttribute("componentList", componentList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/instance/edit";
	}

	@RequestMapping("/save.json")
	@ResponseBody
	public Boolean saveInstanceData(@RequestParam("instanceId") Long instanceId, @RequestParam("jsonStr") String jsonStr, Model model) {
		try {
			if (null == instanceId || StringUtils.isEmpty(jsonStr)) {
				return false;
			}
			templatePageInstanceService.saveTemplatePageInstanceValue(instanceId, jsonStr);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
