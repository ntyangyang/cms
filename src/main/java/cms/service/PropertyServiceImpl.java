package cms.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cms.bean.PropertyQueryBean;
import cms.constants.CmsConstants;
import cms.mapper.ComponentPropertyRlatMapper;
import cms.mapper.PropertyMapper;
import cms.pojo.ComponentProperty;
import cms.pojo.ComponentPropertyRlat;

@Service
@Transactional
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	private PropertyMapper propertyMapper;

	@Autowired
	private ComponentPropertyRlatMapper componentPropertyRlatMapper;

	@Override
	public PageInfo<ComponentProperty> findComponentPropertiesByQueryBeanWithPage(PropertyQueryBean queryBean) {
		PageHelper.startPage(queryBean.getStart(), queryBean.getLength(), true);
		List<ComponentProperty> list = propertyMapper.findComponentPropertiesByCompoentIdWithPage(queryBean.getComponentId());
		PageInfo<ComponentProperty> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public void deleteProperty(Long propertyId) {
		ComponentProperty componentProperty = new ComponentProperty();
		componentProperty.setId(propertyId);
		componentProperty.setLifecycle(2);
		componentProperty.setModifyTime(new Date());
		propertyMapper.updateByPrimaryKeySelective(componentProperty);
	}

	@Override
	public void saveProperty(ComponentProperty property, Long componentId) {
		if (null == property.getId()) {
			property.setCreateTime(new Date());
			int insert = propertyMapper.insert(property);
			if (insert == 1) {
				ComponentPropertyRlat componentPropertyRlat = new ComponentPropertyRlat();
				componentPropertyRlat.setComponentId(componentId);
				componentPropertyRlat.setPropertyId(property.getId());
				componentPropertyRlatMapper.insert(componentPropertyRlat);
			}
		} else {
			property.setModifyTime(new Date());
			propertyMapper.updateByPrimaryKeySelective(property);
		}
	}

	@Override
	public ComponentProperty findPropertyById(Long propertyId) {
		return propertyMapper.selectByPrimaryKey(propertyId);
	}

	@Override
	public Map<String, List<ComponentProperty>> findPropertyMapByComponentId(Long componentId) {
		Map<String, List<ComponentProperty>> componentPropertyMap = new HashMap<String, List<ComponentProperty>>();
		List<ComponentProperty> componentPropertyList = propertyMapper.findPropertiesByComponentId(componentId);
		if (null != componentPropertyList && componentPropertyList.size() > 0) {
			componentPropertyMap.put(componentId.toString(), componentPropertyList);
			for (ComponentProperty componentProperty : componentPropertyList) {
				if (CmsConstants.QUOTE_COMPONENT_SELECT.equals(componentProperty.getType())) {
					Long twoComponentId = Long.valueOf(componentProperty.getExpand());
					List<ComponentProperty> componentPropertyListTwo = propertyMapper.findPropertiesByComponentId(twoComponentId);
					if (null != componentPropertyListTwo && componentPropertyListTwo.size() > 0) {
						componentPropertyMap.put(twoComponentId.toString(), componentPropertyListTwo);
						for (ComponentProperty componentPropertyTwo : componentPropertyListTwo) {
							if (CmsConstants.QUOTE_COMPONENT_SELECT.equals(componentPropertyTwo.getType())) {
								Long threeComponentId = Long.valueOf(componentPropertyTwo.getExpand());
								List<ComponentProperty> componentPropertyListThree = propertyMapper.findPropertiesByComponentId(threeComponentId);
								if (null != componentPropertyListThree && componentPropertyListThree.size() > 0) {
									componentPropertyMap.put(threeComponentId.toString(), componentPropertyListThree);
								}
							}
						}
					}
				}
			}
		}
		return componentPropertyMap;
	}

}
