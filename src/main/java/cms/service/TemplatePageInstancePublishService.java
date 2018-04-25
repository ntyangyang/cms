package cms.service;

import java.util.Date;
import java.util.List;

import cms.pojo.TemplatePageInstancePublish;

public interface TemplatePageInstancePublishService {

	void save(TemplatePageInstancePublish instancePublish);

	TemplatePageInstancePublish getCurrentTemplatePageInstancePublishData(String path, Date date);

	List<String> getTemplatePageInstancePublishUrls();

}
