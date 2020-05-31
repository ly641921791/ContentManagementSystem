package io.github.jaychoufans.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.jaychoufans.cms.common.ApiResponse;
import io.github.jaychoufans.cms.model.SystemRole;
import io.github.jaychoufans.cms.model.SystemUser;
import io.github.jaychoufans.cms.model.SystemUserRole;
import io.github.jaychoufans.cms.service.SystemMenuService;
import io.github.jaychoufans.cms.service.SystemRoleService;
import io.github.jaychoufans.cms.service.SystemUserRoleService;
import io.github.jaychoufans.cms.service.SystemUserService;
import io.github.jaychoufans.cms.utils.WebUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
	private SystemMenuService systemMenuService;

	@Resource
	private SystemUserRoleService systemUserRoleService;

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

	@GetMapping("/list")
	public ApiResponse<?> list(long page, long limit) {
		Page<SystemUser> pageBean = new Page<>(page, limit);
		systemUserService.page(pageBean);
		return ApiResponse.ok(pageBean);
	}

	@GetMapping("/menu")
	public ApiResponse<?> menu() {
		return ApiResponse.ok(systemMenuService.list());
	}

	@PostMapping(name = "设置用户角色", path = "/{id}/role")
	public ApiResponse<?> setUserRole(@PathVariable String id, @RequestBody List<Long> roleIds) {
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
