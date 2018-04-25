package cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cms.bean.ComponentQueryBean;
import cms.pojo.Component;

public interface ComponentService {

	PageInfo<Component> findComponentsByQueryBeanWithPage(ComponentQueryBean queryBean);

	Component findComponentById(Long componentId);

	void saveComponent(Component component);

	void deleteComponent(Long componentId);

	List<Component> findAllQuoteComponents();

	List<Component> findComponentsExcludeById(Long componentId);

	List<Component> findAllComponentsByTemplateId(Long id);

}
