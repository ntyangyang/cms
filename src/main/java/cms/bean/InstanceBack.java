package cms.bean;

import java.io.Serializable;

public class InstanceBack implements Serializable {

	private static final long serialVersionUID = -9084265673953795112L;

	private Boolean success;

	private Long id;

	private String description;

	private String url;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public InstanceBack() {
		super();
	}

	public InstanceBack(Boolean success, Long id, String description, String url) {
		super();
		this.success = success;
		this.id = id;
		this.description = description;
		this.url = url;
	}

}
