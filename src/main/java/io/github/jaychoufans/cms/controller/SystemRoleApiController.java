package io.github.jaychoufans.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.jaychoufans.cms.common.ApiResponse;
import io.github.jaychoufans.cms.model.SystemRole;
import io.github.jaychoufans.cms.service.SystemRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/v1/system/role")
public class SystemRoleApiController {

	@Resource
	private SystemRoleService systemRoleService;

	@GetMapping("/list")
	public ApiResponse<?> list(@RequestParam(required = false, defaultValue = "1") long page,
			@RequestParam(required = false, defaultValue = "10") long limit) {
		Page<SystemRole> pageBean = new Page<>(page, limit);
		systemRoleService.page(pageBean);
		return ApiResponse.ok(pageBean);
	}

}
