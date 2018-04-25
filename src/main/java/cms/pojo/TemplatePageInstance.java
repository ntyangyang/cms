package cms.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "TEMPLATE_PAGE_INSTANCE")
public class TemplatePageInstance implements Serializable {

	private static final long serialVersionUID = 1154330257576828996L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/** ID */
	private Long id;
	/** 模板页面URL */
	private String url;
	/** 使用的模板ID */
	private Long templateId;
	/** 页面类型 */
	private String pageType;
	/** 模块实例集合 */
	private String components;
	/** 模块页面实例数据 */
	private String valueCn;
	/** 生命周期 */
	private Integer lifecycle;
	/** 最新编辑时间 */
	private Date lastModifyTime;
	/** 实例描述 */
	private String description;

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

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public String getComponents() {
		return components;
	}

	public void setComponents(String components) {
		this.components = components;
	}

	public Integer getLifecycle() {
		return lifecycle;
	}

	public void setLifecycle(Integer lifecycle) {
		this.lifecycle = lifecycle;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public String getValueCn() {
		return valueCn;
	}

	public void setValueCn(String valueCn) {
		this.valueCn = valueCn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



}
