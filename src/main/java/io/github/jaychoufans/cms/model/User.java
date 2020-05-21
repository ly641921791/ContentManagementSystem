package io.github.jaychoufans.cms.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.util.Date;

@Data
public class User {

	@TableId
	private Long id;

	private String username;

	private String password;

	private String trueName;

	private String email;

	private String phone;

	private Boolean enabled;

	@Version
	private Integer version;

	private Boolean isDeleted;

	private Long createId;

	private Date createTime;

	private Long updateId;

	private Date updateTime;

}
