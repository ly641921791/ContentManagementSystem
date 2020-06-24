package io.github.jaychoufans.cms.model.view;

import lombok.Data;

@Data
public class DocumentationLendView {

	private Long id;

	private Long userId;

	private String userName;

	private Long documentationId;

	private String documentationName;

	private Integer state;

	private String stateName;

}
