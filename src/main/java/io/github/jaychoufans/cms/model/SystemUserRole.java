package io.github.jaychoufans.cms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.util.Date;

@Data
public class SystemUserRole {

	@TableId(type = IdType.AUTO)
	private Long id;

	private Long userId;

	private Long roleId;

	@Version
	private Integer version;

	private Boolean isDeleted;

	private Long createId;

	private Date createTime;

	private Long updateId;

	private Date updateTime;

}
