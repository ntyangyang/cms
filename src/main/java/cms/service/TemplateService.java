package cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cms.bean.CmsTemplateQueryBean;
import cms.pojo.CmsTemplate;
import cms.pojo.TemplateComponentRlat;

public interface TemplateService {

	PageInfo<CmsTemplate> findTemplatesByQueryBeanWithPage(CmsTemplateQueryBean queryBean);

	void saveCmsTemplate(CmsTemplate cmsTemplate);

	void deleteCmsTemplate(Long templateId);

	List<Long> findComponentsByTemplateId(Long templateId);

	TemplateComponentRlat findTemplateComponentRlatByParam(Long templateId, Long componentId);

	void bindComponent(Long templateId, Long componentId);

	void unBindComponent(Long templateId, Long componentId);

	CmsTemplate findTemplateById(Long templateId);

	List<CmsTemplate> findAllTemplate();

}
