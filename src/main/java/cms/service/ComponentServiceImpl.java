package cms.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cms.bean.ComponentQueryBean;
import cms.mapper.ComponentMapper;
import cms.pojo.Component;

@Service
@Transactional
public class ComponentServiceImpl implements ComponentService {

	@Autowired
	private ComponentMapper componentMapper;
	
	@Override
	public PageInfo<Component> findComponentsByQueryBeanWithPage(ComponentQueryBean queryBean) {
		PageHelper.startPage(queryBean.getStart(), queryBean.getLength(), true);
		Example example = new Example(Component.class);
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(queryBean.getName())) {
			criteria.andEqualTo("name", queryBean.getName());
		}
		if (StringUtils.isNotEmpty(queryBean.getCode())) {
			criteria.andEqualTo("code", queryBean.getCode());
		}
		if (StringUtils.isNotEmpty(queryBean.getType())) {
			criteria.andEqualTo("type", queryBean.getType());
		}
		if (null != queryBean.getLifecycle()) {
			criteria.andEqualTo("lifecycle", queryBean.getLifecycle());
		} else {
			criteria.andNotEqualTo("lifecycle", 2);
		}
		List<Component> list = componentMapper.selectByExample(example);
		PageInfo<Component> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public Component findComponentById(Long componentId) {
		return componentMapper.selectByPrimaryKey(componentId);
	}

	@Override
	public void saveComponent(Component component) {
		if (null == component.getId()) {
			component.setCreateTime(new Date());
			componentMapper.insert(component);
		} else {
			component.setModifyTime(new Date());
			componentMapper.updateByPrimaryKeySelective(component);
		}
	}

	@Override
	public void deleteComponent(Long componentId) {
		Component component = new Component();
		component.setId(componentId);
		component.setLifecycle(2);
		component.setModifyTime(new Date());
		componentMapper.updateByPrimaryKeySelective(component);
	}

	@Override
	public List<Component> findAllQuoteComponents() {
		Example example = new Example(Component.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("type", "QUOTE_COMPONENT").andEqualTo("lifecycle", 1);
		return componentMapper.selectByExample(example);
	}

	@Override
	public List<Component> findComponentsExcludeById(Long componentId) {
		Example example = new Example(Component.class);
		Criteria criteria = example.createCriteria();
		if (null != componentId) {
			criteria.andNotEqualTo("id", componentId);
		}
		criteria.andEqualTo("lifecycle", 1).andEqualTo("type", "INSTANCE_COMPONENT");
		return  componentMapper.selectByExample(example);
	}

	@Override
	public List<Component> findAllComponentsByTemplateId(Long id) {
		return componentMapper.findAllComponentsByTemplateId(id);
	}

}
