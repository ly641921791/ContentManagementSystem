package io.github.jaychoufans.cms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.util.Date;

@Data
public class BookLend {

	/**
	 * 借书未取书
	 */
	public static final Integer lendNotGet = 0;

	/**
	 * 借书已取书
	 */
	public static final Integer getBook = 1;

	/**
	 * 已归还图书
	 */
	public static final Integer returnBook = 2;

	@TableId(type = IdType.AUTO)
	private Long id;

	private Long userId;

	private Long bookId;

	private Date lendTime;

	private Date returnTime;

	private Integer state;

	@Version
	private Integer version;

	private Boolean isDeleted;

	private Long createId;

	private Date createTime;

	private Long updateId;

	private Date updateTime;

}
