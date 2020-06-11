package io.github.jaychoufans.cms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class SystemUserRole {

	@TableId(type = IdType.AUTO)
	private Long id;

	private Long userId;

	private Long roleId;

	@Version
	private Integer version;

	@JsonProperty("isDeleted")
	@TableField("is_deleted")
	private Boolean deleted;

	private Long createId;

	private Date createTime;

	private Long updateId;

	private Date updateTime;

	public SystemUserRole(Long userId, Long roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

}
