package cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cms.bean.ComponentQueryBean;
import cms.bean.Pagination;
import cms.pojo.Component;
import cms.service.ComponentService;

@RequestMapping("/cms/component")
@Controller
public class ComponentController {

	@Autowired
	private ComponentService componentService;

	@RequestMapping(value = "/list.htm", method = RequestMethod.GET)
	public String goToComponentListPage(HttpServletRequest request, Model model) {
		return "/component/list";
	}

	@RequestMapping(value = "/page.json")
	@ResponseBody
	public Pagination<Component> componentListPage(ComponentQueryBean queryBean, Model model) {
		PageInfo<Component> pageInfo = componentService.findComponentsByQueryBeanWithPage(queryBean);
		Pagination<Component> pagination = new Pagination<>();
		pagination.setDraw(queryBean.getDraw());
		pagination.setRecordsTotal(pageInfo.getTotal());
		pagination.setRecordsFiltered(pageInfo.getSize());
		pagination.setData(pageInfo.getList());
		return pagination;
	}

	@RequestMapping("/findComponentById.json")
	@ResponseBody
	public Component findComponentById(Long componentId, Model model) {
		try {
			Component component = null;
			component = componentService.findComponentById(componentId);
			return component;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping("/save.json")
	@ResponseBody
	public Boolean saveComponent(Component component, Model model) {
		try {
			componentService.saveComponent(component);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RequestMapping("/delete.json")
	@ResponseBody
	public Boolean deleteComponent(Long componentId, Model model) {
		try {
			// 查询模块是否已经被模板使用，如果是，则删除失败
			componentService.deleteComponent(componentId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RequestMapping("/findComponentsExcludeById.json")
    @ResponseBody
    public List<Component> findComponentsExcludeById(Long componentId,Model model) {
        try{
            List<Component> components = null;
            components = componentService.findComponentsExcludeById(componentId);
            return components;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
