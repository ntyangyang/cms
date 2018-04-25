package cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;

import cms.bean.CmsTemplateQueryBean;
import cms.bean.Pagination;
import cms.pojo.CmsTemplate;
import cms.service.TemplateService;

@RequestMapping("/cms/template")
@Controller
public class TemplateController {

	@Autowired
	private TemplateService templateService;

	@RequestMapping(value = "/list.htm", method = RequestMethod.GET)
	public String goToTemplateListPage(HttpServletRequest request, Model model) {
		return "/template/list";
	}

	@RequestMapping("/page.json")
	@ResponseBody
	public Pagination<CmsTemplate> templateListPage(CmsTemplateQueryBean queryBean, Model model) {
		PageInfo<CmsTemplate> pageInfo = templateService.findTemplatesByQueryBeanWithPage(queryBean);
		Pagination<CmsTemplate> pagination = new Pagination<>();
		pagination.setDraw(queryBean.getDraw());
		pagination.setRecordsTotal(pageInfo.getTotal());
		pagination.setRecordsFiltered(pageInfo.getSize());
		pagination.setData(pageInfo.getList());
		return pagination;
	}

	@RequestMapping("/findTemplateById.json")
	@ResponseBody
	public CmsTemplate findTemplateById( Long templateId, Model model) {
		try {
			CmsTemplate template = null;
			template = templateService.findTemplateById(templateId);
			return template;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping("/save.json")
	@ResponseBody
	public Boolean saveTemplate(CmsTemplate cmsTemplate, Model model) {
		try {
			templateService.saveCmsTemplate(cmsTemplate);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RequestMapping("/delete.json")
	@ResponseBody
	public Boolean deleteTemplate(Long templateId, Model model) {
		try {
			// 查询模版是否已经被模板使用，如果是，则删除失败
			templateService.deleteCmsTemplate(templateId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RequestMapping("/findComponentsByTemplateId.json")
	@ResponseBody
	public String findComponentsByTemplateId(Long templateId, Model model) {
		try {
			List<Long> componentIds = null;
			componentIds = templateService.findComponentsByTemplateId(templateId);
			String jsonString = JSON.toJSONString(componentIds);
			return jsonString;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping("/bindComponent.json")
	@ResponseBody
	public void bindComponent(Long templateId, Long componentId, boolean type, Model model) {
		try {
			if (type) {
				if (null == templateService.findTemplateComponentRlatByParam(templateId, componentId)) {
					templateService.bindComponent(templateId, componentId);
				}
			} else {
				templateService.unBindComponent(templateId, componentId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
