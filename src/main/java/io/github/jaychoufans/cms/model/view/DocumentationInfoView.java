package io.github.jaychoufans.cms.model.view;

import lombok.Data;

@Data
public class DocumentationInfoView {

	private Long id;

	private String isbn;

	private String name;

	private String author;

	private String publisher;

	private Long type;

	private String typeName;

	private String introduction;

	private String shelf;

	private Integer total;

	private Integer remaining;

	private Integer version;

}
