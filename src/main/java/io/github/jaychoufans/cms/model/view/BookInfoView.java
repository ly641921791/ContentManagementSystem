package io.github.jaychoufans.cms.model.view;

import lombok.Data;

@Data
public class BookInfoView {

	private Long id;

	private String isbn;

	private String name;

	private String author;

	private Long publish;

	private Long type;

	private String typeName;

	private String introduction;

	private String shelf;

	private Integer total;

	private Integer remaining;

	private Integer version;

}
