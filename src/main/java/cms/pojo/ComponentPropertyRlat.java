package cms.pojo;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "COMPONENT_PROPERTY_RLAT")
public class ComponentPropertyRlat implements Serializable {

	private static final long serialVersionUID = 7482274837564959336L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/** ID */
	private Long id;
	/** 属性ID */
	private Long propertyId;
	/** 模块ID */
	private Long componentId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Long propertyId) {
		this.propertyId = propertyId;
	}

	public Long getComponentId() {
		return componentId;
	}

	public void setComponentId(Long componentId) {
		this.componentId = componentId;
	}

}
