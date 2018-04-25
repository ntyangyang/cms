package cms.pojo;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "TEMPLATE_COMPONENT_RLAT")
public class TemplateComponentRlat implements Serializable {

	private static final long serialVersionUID = -730672170333846384L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/** ID */
	private Long id;
	/** 模板ID */
	private Long templateId;
	/** 模块ID */
	private Long componentId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public Long getComponentId() {
		return componentId;
	}

	public void setComponentId(Long componentId) {
		this.componentId = componentId;
	}

}
