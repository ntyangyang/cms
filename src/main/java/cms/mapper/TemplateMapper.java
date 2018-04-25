package cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;

import cms.pojo.CmsTemplate;
import cms.pojo.TemplateComponentRlat;

public interface TemplateMapper extends Mapper<CmsTemplate> {

	List<Long> findComponentsByTemplateId(@Param("templateId") Long templateId);

	TemplateComponentRlat findTemplateComponentRlatByParam(@Param("templateId") Long templateId, @Param("componentId") Long componentId);

}
