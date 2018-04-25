package cms.bean;

import java.io.Serializable;

public class PreviewBack implements Serializable {
	
	private static final long serialVersionUID = -8821137095401501050L;

	private Boolean success;
	
	private String destination;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
}
