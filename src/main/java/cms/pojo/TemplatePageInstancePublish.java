package cms.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "TEMPLATE_PAGE_INSTANCE_PUBLISH")
public class TemplatePageInstancePublish implements Serializable {

	private static final long serialVersionUID = 4133022507561155714L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/** ID */
	private Long id;
	/** 页面链接URL */
	private String url;
	/** 模板页面ID */
	private Long templatePageInstanceId;
	/** 页面类型 */
	private String pageType;
	/** 实例版本号 */
	private String publishVersion;
	/** 模板页面模块集合 */
	private String components;
	/** 模板页面实例数据 */
	private String valueCn;
	/** 生命周期 */
	private Integer lifecycle;
	/** 发布时间 */
	private Date publishTime;
	/** 生效时间 */
	private Date startTime;
	/** 结束时间 */
	private Date endTime;
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

	public Long getTemplatePageInstanceId() {
		return templatePageInstanceId;
	}

	public void setTemplatePageInstanceId(Long templatePageInstanceId) {
		this.templatePageInstanceId = templatePageInstanceId;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public String getPublishVersion() {
		return publishVersion;
	}

	public void setPublishVersion(String publishVersion) {
		this.publishVersion = publishVersion;
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

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	public TemplatePageInstancePublish() {
		super();
	}

	public TemplatePageInstancePublish(Long id, String url, Long templatePageInstanceId, String pageType, String publishVersion, String components,
			String valueCn, Integer lifecycle, Date publishTime, Date startTime, Date endTime, String description) {
		super();
		this.id = id;
		this.url = url;
		this.templatePageInstanceId = templatePageInstanceId;
		this.pageType = pageType;
		this.publishVersion = publishVersion;
		this.components = components;
		this.valueCn = valueCn;
		this.lifecycle = lifecycle;
		this.publishTime = publishTime;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
	}

}
