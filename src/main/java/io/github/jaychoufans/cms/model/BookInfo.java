package io.github.jaychoufans.cms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.util.Date;

@Data
public class BookInfo {

	@TableId(type = IdType.AUTO)
	private Long id;

	private String isbn;

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

	private Boolean isDeleted;

	private Long createId;

	private Date createTime;

	private Long updateId;

	private Date updateTime;

}
