package cms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cms.bean.InstanceBack;
import cms.bean.Pagination;
import cms.bean.PreviewBack;
import cms.bean.TemplatePageInstanceQueryBean;
import cms.pojo.CmsTemplate;
import cms.pojo.TemplatePageInstance;
import cms.pojo.TemplatePageInstanceCommand;
import cms.pojo.TemplatePageInstancePublish;
import cms.service.TemplatePageInstancePublishService;
import cms.service.TemplatePageInstanceService;
import cms.service.TemplateService;

@RequestMapping("/cms/instance")
@Controller
public class TemplatePageInstanceController {

	@Autowired
	private TemplatePageInstanceService templatePageInstanceService;

	@Autowired
	private TemplateService templateService;

	@Autowired
	private TemplatePageInstancePublishService templatePageInstancePublishService;

	@RequestMapping(value = "/list.htm", method = RequestMethod.GET)
	public String goToTemplateInstanceListPage(HttpServletRequest request, Model model) {
		List<CmsTemplate> templateList = templateService.findAllTemplate();
		model.addAttribute("templateList", templateList);
		return "/instance/list";
	}

	@RequestMapping("/page.json")
	@ResponseBody
	public Pagination<TemplatePageInstanceCommand> templateListPage(TemplatePageInstanceQueryBean queryBean, Model model) {
		PageInfo<TemplatePageInstanceCommand> pageInfo = templatePageInstanceService.findTemplatePageInstanceQueryBeanWithPage(queryBean);
		Pagination<TemplatePageInstanceCommand> pagination = new Pagination<>();
		pagination.setDraw(queryBean.getDraw());
		pagination.setRecordsTotal(pageInfo.getTotal());
		pagination.setRecordsFiltered(pageInfo.getSize());
		pagination.setData(pageInfo.getList());
		return pagination;
	}

	@RequestMapping("/save.json")
	@ResponseBody
	public Boolean TemplatePageInstance(HttpServletRequest request, TemplatePageInstance templatePageInstance, Model model) {
		try {
			templatePageInstanceService.saveTemplatePageInstance(templatePageInstance);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RequestMapping("/delete.json")
	@ResponseBody
	public Boolean deleteTemplatePageInstance(Long instanceId, Model model) {
		try {
			// 查询实例是否已经被使用，如果是，则删除失败
			templatePageInstanceService.deleteTemplatePageInstance(instanceId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RequestMapping("/findById.json")
	@ResponseBody
	public InstanceBack findTemplatePageInstanceById(Long instanceId, Model model) {
		try {
			TemplatePageInstance templatePageInstance = null;
			templatePageInstance = templatePageInstanceService.findTemplatePageInstanceById(instanceId);
			return new InstanceBack(true, instanceId, templatePageInstance.getDescription(), templatePageInstance.getUrl());
		} catch (Exception e) {
			e.printStackTrace();
			return new InstanceBack(false, instanceId, null, null);
		}
	}

	@RequestMapping("/publish.json")
	@ResponseBody
	public InstanceBack findTemplatePageInstanceById(Long instanceId, String url, String startTime, String endTime, Model model) {
		try {
			TemplatePageInstance templatePageInstance = null;
			templatePageInstance = templatePageInstanceService.findTemplatePageInstanceById(instanceId);
			if (null != templatePageInstance) {
				templatePageInstance.setUrl(url);
				templatePageInstance.setLastModifyTime(new Date());
				templatePageInstanceService.saveTemplatePageInstance(templatePageInstance);

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date sTime = sdf.parse(startTime);
				Date eTime = sdf.parse(endTime);
				TemplatePageInstancePublish instancePublish = new TemplatePageInstancePublish(null, url, instanceId, templatePageInstance.getPageType(),
						UUID.randomUUID().toString().replaceAll("-", ""), templatePageInstance.getComponents(), templatePageInstance.getValueCn(), 1,
						new Date(), sTime, eTime, templatePageInstance.getDescription());
				templatePageInstancePublishService.save(instancePublish);
			}
			return new InstanceBack(true, instanceId, null, templatePageInstance.getUrl());
		} catch (Exception e) {
			e.printStackTrace();
			return new InstanceBack(false, instanceId, null, null);
		}
	}

	@RequestMapping("/preview.json")
	@ResponseBody
	public PreviewBack preview(@RequestParam("instanceId") Long instanceId, @RequestParam("type") String type, Model model) {
		PreviewBack result = new PreviewBack();
		try {
			if (null == instanceId) {
				return null;
			}
			
			if (("preview").equals(type)) {
				result.setDestination("/cms/data/preview?previewParam=" + instanceId);
				result.setSuccess(true);
			} else if (("reiview").equals(type)) {
				result.setDestination("/cms/data/review?reviewParam=" + instanceId);
				result.setSuccess(false);
			} else {
				return result;
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			return result;
		}
	}
}
