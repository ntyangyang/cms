package cms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cms.bean.Pagination;
import cms.bean.PropertyQueryBean;
import cms.pojo.Component;
import cms.pojo.ComponentProperty;
import cms.service.ComponentService;
import cms.service.PropertyService;

@RequestMapping("/cms/property")
@Controller
public class PropertyController {
	
	@Autowired
	private ComponentService componentService;
	
	@Autowired
	private PropertyService propertyService;

	@RequestMapping(value = "/list.htm", method = RequestMethod.GET)
    public String goToPropertyListPage(HttpServletRequest request,Model model){
        try{
            Long componentId = Long.parseLong(request.getParameter("componentId"));
            Component component = componentService.findComponentById(componentId);
            List<Component> otherComponents = componentService.findAllQuoteComponents();
            model.addAttribute("component", component);
            model.addAttribute("otherComponents", otherComponents);
        }catch(Exception e){
           e.printStackTrace();
        }
        return "/property/list";
    }
	
	@RequestMapping(value = "/page.json")
	@ResponseBody
	public Pagination<ComponentProperty> propertyListPage(PropertyQueryBean queryBean, Model model) {
		PageInfo<ComponentProperty> pageInfo = propertyService.findComponentPropertiesByQueryBeanWithPage(queryBean);
		Pagination<ComponentProperty> pagination = new Pagination<>();
		pagination.setDraw(queryBean.getDraw());
		pagination.setRecordsTotal(pageInfo.getTotal());
		pagination.setRecordsFiltered(pageInfo.getSize());
		pagination.setData(pageInfo.getList());
		return pagination;
	}
	
	@RequestMapping("/delete.json")
	@ResponseBody
	public Boolean deleteProperty(Long propertyId, Model model) {
		try {
			propertyService.deleteProperty(propertyId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RequestMapping("/save.json")
    @ResponseBody
    public Object saveProperty(ComponentProperty property,Long componentId, Model model) {
        try{
        	propertyService.saveProperty(property, componentId);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
	
	@RequestMapping("/findPropertyById.json")
    @ResponseBody
    public ComponentProperty findPropertyById(Model model,@RequestParam("propertyId") Long propertyId) {
        try{
            return propertyService.findPropertyById(propertyId);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
	
	@RequestMapping("/findPropertyByComponentId.json")
    @ResponseBody
    public Map<String, List<ComponentProperty>> findPropertyMapByComponentId(Model model,@RequestParam("componentId") Long componentId) {
        try{
            return propertyService.findPropertyMapByComponentId(componentId);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
