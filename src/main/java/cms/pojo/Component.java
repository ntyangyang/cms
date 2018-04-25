package cms.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "COMPONENT")
public class Component implements Serializable {

	private static final long serialVersionUID = 1391846475535715883L;

	/** ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/** 编码 */
	private String code;
	/** 属性名称 */
	private String name;
	/** 属性描述 */
	private String description;
	/** 默认值 */
	private String defaultValue;
	/** 状态 */
	private Integer lifecycle;
	/** 类型 */
	private String type;
	/** 创建时间 */
	private Date createTime;
	/** 修改名称 */
	private Date modifyTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Integer getLifecycle() {
		return lifecycle;
	}

	public void setLifecycle(Integer lifecycle) {
		this.lifecycle = lifecycle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "Component [id=" + id + ", code=" + code + ", name=" + name + ", description=" + description + ", defaultValue=" + defaultValue + ", lifecycle="
				+ lifecycle + ", type=" + type + ", createTime=" + createTime + ", modifyTime=" + modifyTime + "]";
	}

}
