package io.github.jaychoufans.cms.model.view;

import lombok.Data;

@Data
public class BookLendView {

	private Long id;

	private Long userId;

	private String userName;

	private Long bookId;

	private String bookName;

	private Integer state;

	private String stateName;

}
