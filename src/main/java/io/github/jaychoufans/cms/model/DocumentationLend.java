package io.github.jaychoufans.cms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class DocumentationLend {

	/**
	 * 借书未取书
	 */
	public static final Integer waitCollect = 0;

	/**
	 * 借书已取书
	 */
	public static final Integer waitReturn = 1;

	/**
	 * 已归还图书
	 */
	public static final Integer alreadyReturn = 2;

	@TableId(type = IdType.AUTO)
	private Long id;

	private Long userId;

	private Long documentationId;

	private Date lendTime;

	private Date returnTime;

	private Integer state;

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
