package cms.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import cms.bean.PropertyQueryBean;
import cms.pojo.ComponentProperty;

public interface PropertyService {

	PageInfo<ComponentProperty> findComponentPropertiesByQueryBeanWithPage(PropertyQueryBean queryBean);

	void deleteProperty(Long propertyId);

	void saveProperty(ComponentProperty property, Long componentId);

	ComponentProperty findPropertyById(Long propertyId);

	Map<String, List<ComponentProperty>> findPropertyMapByComponentId(Long componentId);

}
