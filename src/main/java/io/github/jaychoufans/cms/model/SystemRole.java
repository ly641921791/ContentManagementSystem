package io.github.jaychoufans.cms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * 系统角色
 *
 * @author ly
 */
@Data
public class SystemRole {

	@TableId(type = IdType.AUTO)
	private Long id;

	private String name;

	/**
	 * 是否默认角色。指在用户创建时，默认赋予用户的角色。
	 */
	@JsonProperty("isDefaultRole")
	@TableField("is_default_role")
	private Boolean defaultRole;

	@Version
	private Integer version;

	@JsonProperty("isDeleted")
	@TableField("is_deleted")
	private Boolean deleted;

	private Long createId;

	private Date createTime;

	private Long updateId;

	private Date updateTime;

}
