package cms.bean;

import java.io.Serializable;

public class ImageBack implements Serializable {

	private static final long serialVersionUID = -2626861594464035601L;
	
	private Boolean success;
	
	private String imgUrl;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	

}
