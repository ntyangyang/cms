package cms.bean;

import java.io.Serializable;

public class CmsTemplateQueryBean implements Serializable {

	private static final long serialVersionUID = 5556686632667495435L;

	private Integer draw;

	private Integer start;

	private Integer length;

	private String name;

	private Integer lifecycle;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLifecycle() {
		return lifecycle;
	}

	public void setLifecycle(Integer lifecycle) {
		this.lifecycle = lifecycle;
	}

}
