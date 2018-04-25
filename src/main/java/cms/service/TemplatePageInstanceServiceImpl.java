package cms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cms.bean.TemplatePageInstanceQueryBean;
import cms.mapper.TemplateMapper;
import cms.mapper.TemplatePageInstanceMapper;
import cms.pojo.CmsTemplate;
import cms.pojo.TemplatePageInstance;
import cms.pojo.TemplatePageInstanceCommand;

@Service
@Transactional
public class TemplatePageInstanceServiceImpl implements TemplatePageInstanceService {

	@Autowired
	private TemplatePageInstanceMapper templatePageInstanceMapper;

	@Autowired
	private TemplateMapper templateMapper;

	@Override
	public PageInfo<TemplatePageInstanceCommand> findTemplatePageInstanceQueryBeanWithPage(TemplatePageInstanceQueryBean queryBean) {
		PageHelper.startPage(queryBean.getStart(), queryBean.getLength(), true);
		List<TemplatePageInstanceCommand> list = templatePageInstanceMapper.findTemplatePageInstanceQueryBeanWithPage(queryBean);
		PageInfo<TemplatePageInstanceCommand> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public void saveTemplatePageInstance(TemplatePageInstance templatePageInstance) {
		if (templatePageInstance.getId() == null) {
			CmsTemplate template = templateMapper.selectByPrimaryKey(templatePageInstance.getTemplateId());
			templatePageInstance.setLastModifyTime(new Date());
			templatePageInstance.setLifecycle(1);
			templatePageInstance.setPageType(template.getType());
			templatePageInstanceMapper.insert(templatePageInstance);
		} else {
			templatePageInstanceMapper.updateByPrimaryKeySelective(templatePageInstance);
		}

	}

	@Override
	public void deleteTemplatePageInstance(Long instanceId) {
		TemplatePageInstance instance = new TemplatePageInstance();
		instance.setId(instanceId);
		instance.setLifecycle(2);
		instance.setLastModifyTime(new Date());
		templatePageInstanceMapper.updateByPrimaryKeySelective(instance);

	}

	@Override
	public TemplatePageInstance findTemplatePageInstanceById(Long instanceId) {
		return templatePageInstanceMapper.selectByPrimaryKey(instanceId);
	}

	@Override
	public void saveTemplatePageInstanceValue(Long instanceId, String jsonStr) {
		TemplatePageInstance templatePageInstance = templatePageInstanceMapper.selectByPrimaryKey(instanceId);
		templatePageInstance.setValueCn(jsonStr);
		templatePageInstance.setLastModifyTime(new Date());
		templatePageInstanceMapper.updateByPrimaryKeySelective(templatePageInstance);
	}
}
