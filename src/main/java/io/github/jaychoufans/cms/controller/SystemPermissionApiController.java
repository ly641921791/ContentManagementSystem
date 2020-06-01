package io.github.jaychoufans.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.jaychoufans.cms.common.ApiResponse;
import io.github.jaychoufans.cms.model.SystemPermission;
import io.github.jaychoufans.cms.service.SystemPermissionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/v1/system/permission")
public class SystemPermissionApiController {

	@Resource
	private SystemPermissionService systemPermissionService;

	@GetMapping("/list")
	public ApiResponse<?> list(@RequestParam(required = false, defaultValue = "1") long page,
			@RequestParam(required = false, defaultValue = "10") long limit) {
		Page<SystemPermission> pageBean = new Page<>(page, limit);
		systemPermissionService.page(pageBean);
		return ApiResponse.ok(pageBean);
	}

}
