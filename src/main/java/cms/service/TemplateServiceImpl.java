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

import cms.bean.CmsTemplateQueryBean;
import cms.mapper.TemplateComponentRlatMapper;
import cms.mapper.TemplateMapper;
import cms.pojo.CmsTemplate;
import cms.pojo.TemplateComponentRlat;

@Service
@Transactional
public class TemplateServiceImpl implements TemplateService {

	@Autowired
	private TemplateMapper templateMapper;
	
	@Autowired
	private TemplateComponentRlatMapper templateComponentRlatMapper;

	@Override
	public PageInfo<CmsTemplate> findTemplatesByQueryBeanWithPage(CmsTemplateQueryBean queryBean) {
		PageHelper.startPage(queryBean.getStart(), queryBean.getLength(), true);
		Example example = new Example(CmsTemplate.class);
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(queryBean.getName())) {
			criteria.andEqualTo("name", queryBean.getName());
		}
		if (null != queryBean.getLifecycle()) {
			criteria.andEqualTo("lifecycle", queryBean.getLifecycle());
		} else {
			criteria.andNotEqualTo("lifecycle", 2);
		}
		List<CmsTemplate> list = templateMapper.selectByExample(example);
		PageInfo<CmsTemplate> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public void saveCmsTemplate(CmsTemplate cmsTemplate) {
		if (null == cmsTemplate.getId()) {
			cmsTemplate.setCreateTime(new Date());
			templateMapper.insert(cmsTemplate);
		} else {
			cmsTemplate.setModifyTime(new Date());
			templateMapper.updateByPrimaryKeySelective(cmsTemplate);
		}
	}

	@Override
	public void deleteCmsTemplate(Long templateId) {
		CmsTemplate template = new CmsTemplate();
		template.setId(templateId);
		template.setLifecycle(2);
		template.setModifyTime(new Date());
		templateMapper.updateByPrimaryKeySelective(template);

	}

	@Override
	public List<Long> findComponentsByTemplateId(Long templateId) {
		return templateMapper.findComponentsByTemplateId(templateId);
	}

	@Override
	public TemplateComponentRlat findTemplateComponentRlatByParam(Long templateId, Long componentId) {
		return templateMapper.findTemplateComponentRlatByParam(templateId, componentId);
	}

	@Override
	public void bindComponent(Long templateId, Long componentId) {
		TemplateComponentRlat templateComponentRlat = new TemplateComponentRlat();
		templateComponentRlat.setComponentId(componentId);
		templateComponentRlat.setTemplateId(templateId);
		templateComponentRlatMapper.insert(templateComponentRlat);
	}

	@Override
	public void unBindComponent(Long templateId, Long componentId) {
		TemplateComponentRlat templateComponentRlat = new TemplateComponentRlat();
		templateComponentRlat.setComponentId(componentId);
		templateComponentRlat.setTemplateId(templateId);
		templateComponentRlatMapper.delete(templateComponentRlat);
	}

	@Override
	public CmsTemplate findTemplateById(Long templateId) {
		return templateMapper.selectByPrimaryKey(templateId);
	}

	@Override
	public List<CmsTemplate> findAllTemplate() {
		return templateMapper.select(null);
	}
}
