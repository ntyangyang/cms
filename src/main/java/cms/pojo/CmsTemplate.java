package cms.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "TEMPLATE")
public class CmsTemplate implements Serializable {

	private static final long serialVersionUID = -8841770331947045897L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/** ID */
	private Long id;
	/** 模板名称 */
	private String name;
	/** 模板描述 */
	private String description;
	/** layout类型 */
	private String type;
	/** 分区数目 */
	private Long partitionNum;
	/** 状态 */
	private Integer lifecycle;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getPartitionNum() {
		return partitionNum;
	}

	public void setPartitionNum(Long partitionNum) {
		this.partitionNum = partitionNum;
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

}
