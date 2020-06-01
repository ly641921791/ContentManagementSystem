package io.github.jaychoufans.cms.model.view;

import lombok.Data;

import java.util.List;

@Data
public class SystemUserView {

	private Long id;

	private String username;

	private String trueName;

	private String email;

	private String phone;

	private Boolean enabled;

	private Integer version;

	private String role;

	private List<Long> roleIds;

}
