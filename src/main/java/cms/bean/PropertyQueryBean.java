package cms.bean;

import java.io.Serializable;

public class PropertyQueryBean implements Serializable {

	private static final long serialVersionUID = -8375997223404466104L;

	private Integer draw;

	private Integer start;

	private Integer length;

	private Long componentId;

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start + 1;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Long getComponentId() {
		return componentId;
	}

	public void setComponentId(Long componentId) {
		this.componentId = componentId;
	}

}
