package cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;

import cms.bean.TemplatePageInstanceQueryBean;
import cms.pojo.TemplatePageInstance;
import cms.pojo.TemplatePageInstanceCommand;

public interface TemplatePageInstanceMapper extends Mapper<TemplatePageInstance> {

	List<TemplatePageInstanceCommand> findTemplatePageInstanceQueryBeanWithPage(@Param("queryBean")TemplatePageInstanceQueryBean queryBean);

}
