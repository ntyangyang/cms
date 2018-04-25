package cms.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "PROPERTY")
public class ComponentProperty implements Serializable {

	private static final long serialVersionUID = 529157662827332698L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/** id */
	private Long id;
	/** 属性名 */
	private String name;
	/** 属性键值 */
	private String keyword;
	/** 属性类型 */
	private String type;
	/** 扩展参数 */
	private String expand;
	/** 引用组件的循环次数 */
	private Integer cycleIndex;
	/** 属性值类型 */
	private String paramType;
	/** 属性描述 */
	private String description;
	/** 生命周期 */
	private Integer lifecycle;
	/** 序号 */
	private Integer orderNum;
	/** 创建时间 */
	private Date createTime;
	/** 修改时间 */
	private Date modifyTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExpand() {
		return expand;
	}

	public void setExpand(String expand) {
		this.expand = expand;
	}

	public Integer getCycleIndex() {
		return cycleIndex;
	}

	public void setCycleIndex(Integer cycleIndex) {
		this.cycleIndex = cycleIndex;
	}

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLifecycle() {
		return lifecycle;
	}

	public void setLifecycle(Integer lifecycle) {
		this.lifecycle = lifecycle;
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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

}
