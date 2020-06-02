package io.github.jaychoufans.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.jaychoufans.cms.common.ApiResponse;
import io.github.jaychoufans.cms.model.SystemRole;
import io.github.jaychoufans.cms.model.SystemRolePermission;
import io.github.jaychoufans.cms.service.SystemRolePermissionService;
import io.github.jaychoufans.cms.service.SystemRoleService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/system/role")
public class SystemRoleApiController {

	@Resource
	private SystemRoleService systemRoleService;

	@Resource
	private SystemRolePermissionService systemRolePermissionService;

	@GetMapping("/list")
	public ApiResponse<?> list(@RequestParam(required = false, defaultValue = "1") long page,
			@RequestParam(required = false, defaultValue = "10") long limit) {
		Page<SystemRole> pageBean = new Page<>(page, limit);
		systemRoleService.page(pageBean);
		return ApiResponse.ok(pageBean);
	}

	@PostMapping(name = "设置角色权限", path = "/{id}/permission")
	public ApiResponse<?> setRolePermission(@PathVariable Long id, @RequestBody List<Long> permissionIds) {
		// 删除原有权限
		SystemRolePermission entity = new SystemRolePermission();
		entity.setRoleId(id);
		systemRolePermissionService.remove(new QueryWrapper<>(entity));
		// 插入新增权限
		if (CollectionUtils.isNotEmpty(permissionIds)) {
			List<SystemRolePermission> entityList = permissionIds.stream()
					.map(permissionId -> new SystemRolePermission(id, permissionId)).collect(Collectors.toList());
			systemRolePermissionService.saveBatch(entityList);
		}
		return ApiResponse.ok();
	}

	@GetMapping(name = "获取角色权限", path = "/{id}/permission")
	public ApiResponse<?> getRolePermission(@PathVariable Long id) {
		SystemRolePermission entity = new SystemRolePermission();
		entity.setRoleId(id);
		List<SystemRolePermission> data = systemRolePermissionService.list(new QueryWrapper<>(entity));
		return ApiResponse.ok(data);
	}

}
