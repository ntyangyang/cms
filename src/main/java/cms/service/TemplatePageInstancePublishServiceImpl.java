package cms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.entity.Example;

import cms.mapper.TemplatePageInstancePublishMapper;
import cms.pojo.TemplatePageInstancePublish;

@Service
@Transactional
public class TemplatePageInstancePublishServiceImpl implements TemplatePageInstancePublishService {

	@Autowired
	private TemplatePageInstancePublishMapper templatePageInstancePublishMapper;

	@Override
	public void save(TemplatePageInstancePublish instancePublish) {
		templatePageInstancePublishMapper.insert(instancePublish);
	}

	@Override
	public TemplatePageInstancePublish getCurrentTemplatePageInstancePublishData(String path, Date curDate) {
		TemplatePageInstancePublish returnTemplatePageInstancePublish = null;

		Example example = new Example(TemplatePageInstancePublish.class);
		example.createCriteria().andEqualTo("url", path);
		List<TemplatePageInstancePublish> list = templatePageInstancePublishMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return null;
		}

		// 发布时，会将历史记录的结束时间改为当前版本的开始时间，需判断时间段是否有效
		for (TemplatePageInstancePublish temp : list) {
			// templatePageInstancePublishsList按照开始时间逆序排列，数据正常的话，后发布的在前面，以下循环判断curDate是否在有效时间范围了，
			if (temp.getStartTime().before(curDate) && null == temp.getEndTime()) {
				returnTemplatePageInstancePublish = temp;
				break;
			} else if (temp.getStartTime().before(curDate) && curDate.before(temp.getEndTime())) {
				returnTemplatePageInstancePublish = temp;
				break;
			}
		}
		return returnTemplatePageInstancePublish;
	}

	@Override
	public List<String> getTemplatePageInstancePublishUrls() {
		// TODO Auto-generated method stub
		return null;
	}

}
