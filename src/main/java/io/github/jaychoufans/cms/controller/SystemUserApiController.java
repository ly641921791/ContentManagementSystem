package io.github.jaychoufans.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Strings;
import io.github.jaychoufans.cms.common.ApiResponse;
import io.github.jaychoufans.cms.model.SystemRole;
import io.github.jaychoufans.cms.model.SystemUser;
import io.github.jaychoufans.cms.model.SystemUserRole;
import io.github.jaychoufans.cms.model.view.SystemUserView;
import io.github.jaychoufans.cms.service.SystemPermissionService;
import io.github.jaychoufans.cms.service.SystemRoleService;
import io.github.jaychoufans.cms.service.SystemUserRoleService;
import io.github.jaychoufans.cms.service.SystemUserService;
import io.github.jaychoufans.cms.utils.WebUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/system/user")
public class SystemUserApiController {

	@Resource
	private SystemUserService systemUserService;

	@Resource
	private SystemRoleService systemRoleService;

	@Resource
	private SystemPermissionService systemPermissionService;

	@Resource
	private SystemUserRoleService systemUserRoleService;

	@PostMapping(name = "新增用户")
	public ApiResponse<?> add(SystemUser user) {
		// 设置默认密码 以及 密码加密
		if (Strings.isNullOrEmpty(user.getPassword())) {
			user.setPassword("123456");
		}
		user.setPassword(DigestUtils.sha512Hex(user.getPassword()));

		systemUserService.save(user);
		return ApiResponse.ok();
	}

	@DeleteMapping(name = "删除用户")
	public ApiResponse<?> del(@RequestBody List<Long> ids) {
		ids.forEach(id -> systemUserService.removeById(id));
		return ApiResponse.ok();
	}

	@PostMapping("/login")
	public ApiResponse<?> login(String username, String password) {
		SystemUser user = systemUserService.findByUsername(username);
		if (user == null) {
			return ApiResponse.error("A0201", "用户账户不存在");
		}
		if (!user.getEnabled()) {
			return ApiResponse.error("A0202", "用户账户被冻结");
		}
		if (!DigestUtils.sha512Hex(password).equals(user.getPassword())) {
			return ApiResponse.error("A0210", "用户密码错误");
		}
		WebUtils.setCurrentUser(user);
		return ApiResponse.ok(user);
	}

	@GetMapping(name = "获取用户列表", path = "/list")
	public ApiResponse<?> list(long page, long limit) {
		Page<SystemUser> pageBean = new Page<>(page, limit);
		systemUserService.page(pageBean);

		return ApiResponse.ok(pageBean, systemUser -> {
			SystemUserView view = new SystemUserView();
			BeanUtils.copyProperties(systemUser, view);

			StringBuilder role = new StringBuilder();
			List<Long> roleIds = new ArrayList<>();

			SystemUserRole entity = new SystemUserRole();
			entity.setUserId(systemUser.getId());
			List<SystemUserRole> userRoles = systemUserRoleService.list(new QueryWrapper<>(entity));
			userRoles.forEach(systemUserRole -> {
				role.append("、").append(systemRoleService.getById(systemUserRole.getRoleId()).getName());
				roleIds.add(systemUserRole.getRoleId());
			});

			view.setRole(role.length() == 0 ? "" : role.substring(1));
			view.setRoleIds(roleIds);

			return view;
		});
	}

	@GetMapping("/menu")
	public ApiResponse<?> menu() {
		return ApiResponse.ok(systemPermissionService.list());
	}

	@PostMapping(name = "设置用户角色", path = "/{id}/role")
	public ApiResponse<?> setUserRole(@PathVariable Long id, @RequestBody List<Long> roleIds) {
		// 删除原有角色
		SystemUserRole entity = new SystemUserRole();
		entity.setUserId(id);
		systemUserRoleService.remove(new QueryWrapper<>(entity));
		// 插入新增角色
		if (CollectionUtils.isNotEmpty(roleIds)) {
			List<SystemUserRole> entityList = roleIds.stream().map(roleId -> new SystemUserRole(id, roleId))
					.collect(Collectors.toList());
			systemUserRoleService.saveBatch(entityList);
		}
		return ApiResponse.ok();
	}

	@GetMapping(name = "获取用户角色", path = "/{id}/role")
	public ApiResponse<?> getUserRole(@PathVariable Long id) {
		SystemUserRole entity = new SystemUserRole();
		entity.setUserId(id);
		List<SystemUserRole> userRoles = systemUserRoleService.list(new QueryWrapper<>(entity));

		List<SystemRole> data = userRoles.stream()
				.map(systemUserRole -> systemRoleService.getById(systemUserRole.getRoleId()))
				.collect(Collectors.toList());

		return ApiResponse.ok(data);
	}

}
