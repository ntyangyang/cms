package cms.pojo;

import java.util.Date;

public class TemplatePageInstanceCommand extends TemplatePageInstance {

	private static final long serialVersionUID = -987945024385243676L;

	private String templateName;

	private Date lastModifyTime;
	
	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

}
