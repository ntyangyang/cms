package cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;

import cms.pojo.Component;

public interface ComponentMapper extends Mapper<Component> {

	List<Component> findAllComponentsByTemplateId(@Param("templateId")Long id);

}
