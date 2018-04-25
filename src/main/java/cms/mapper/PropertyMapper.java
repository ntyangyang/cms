package cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;

import cms.pojo.ComponentProperty;

public interface PropertyMapper extends Mapper<ComponentProperty> {

	List<ComponentProperty> findComponentPropertiesByCompoentIdWithPage(@Param("componentId")Long componentId);

	List<ComponentProperty> findPropertiesByComponentId(@Param("componentId")Long componentId);

}
