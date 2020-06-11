package io.github.jaychoufans.cms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class SystemPermission {

	@TableId(type = IdType.AUTO)
	private Long id;

	private Integer type;

	private String name;

	private String url;

	private String icon;

	private Long parentId;

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
