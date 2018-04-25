package cms.service;

import com.github.pagehelper.PageInfo;

import cms.bean.TemplatePageInstanceQueryBean;
import cms.pojo.TemplatePageInstance;
import cms.pojo.TemplatePageInstanceCommand;

public interface TemplatePageInstanceService {

	PageInfo<TemplatePageInstanceCommand> findTemplatePageInstanceQueryBeanWithPage(TemplatePageInstanceQueryBean queryBean);

	void saveTemplatePageInstance(TemplatePageInstance templatePageInstance);

	void deleteTemplatePageInstance(Long instanceId);

	TemplatePageInstance findTemplatePageInstanceById(Long instanceId);

	void saveTemplatePageInstanceValue(Long instanceId, String jsonStr);

}
