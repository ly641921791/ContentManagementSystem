package io.github.jaychoufans.cms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class BookInfo {

	/**
	 * 数据库字段：书名
	 */
	public static final String NAME = "name";

	@TableId(type = IdType.AUTO)
	private Long id;

	private String isbn;

	/**
	 * 书名
	 */
	@TableField(NAME)
	private String name;

	private String author;

	private String publisher;

	private Long type;

	private String introduction;

	private String shelf;

	private Integer total;

	private Integer remaining;

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
