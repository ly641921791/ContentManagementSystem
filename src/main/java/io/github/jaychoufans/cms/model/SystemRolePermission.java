package io.github.jaychoufans.cms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class SystemRolePermission {

	@TableId(type = IdType.AUTO)
	private Long id;

	private Long roleId;

	private Long permissionId;

	@Version
	private Integer version;

	private Boolean isDeleted;

	private Long createId;

	private Date createTime;

	private Long updateId;

	private Date updateTime;

	public SystemRolePermission(Long roleId, Long permissionId) {
		this.roleId = roleId;
		this.permissionId = permissionId;
	}

}
