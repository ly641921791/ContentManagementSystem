package io.github.jaychoufans.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.jaychoufans.cms.common.ApiResponse;
import io.github.jaychoufans.cms.model.User;
import io.github.jaychoufans.cms.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserApiController {

	@Resource
	private UserService userService;

	@PostMapping("/login")
	public ApiResponse<?> login(String username, String password) {
		User user = userService.findByUsername(username);
		if (user == null) {
			return ApiResponse.error("A0201", "用户账户不存在");
		}
		if (!user.getEnabled()) {
			return ApiResponse.error("A0202", "用户账户被冻结");
		}
		if (!DigestUtils.sha512Hex(password).equals(user.getPassword())) {
			return ApiResponse.error("A0210", "用户密码错误");
		}
		return ApiResponse.ok(user);
	}

	@GetMapping("/list")
	public ApiResponse<?> list(long page, long limit) {
		Page<User> pageBean = new Page<>(page, limit);
		userService.page(pageBean);
		return ApiResponse.ok(pageBean);
	}

}
