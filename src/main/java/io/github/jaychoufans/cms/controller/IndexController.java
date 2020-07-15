package io.github.jaychoufans.cms.controller;

import io.github.jaychoufans.cms.common.Const;
import io.github.jaychoufans.cms.model.SystemConfig;
import io.github.jaychoufans.cms.service.SystemConfigService;
import io.github.jaychoufans.cms.service.SystemUserService;
import io.github.jaychoufans.cms.utils.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

	@Resource
	private SystemUserService systemUserService;

	@Resource
	private SystemConfigService systemConfigService;

	@GetMapping("/")
	public String index() {
		if (WebUtils.getCurrentUser() == null) {
			SystemConfig visitorStatus = systemConfigService.getByKey(Const.visitorStatus);
			// 支持游客功能
			if (visitorStatus != null && Const.visitorStatusEnable.equals(visitorStatus.getConfigValue())) {
				WebUtils.setCurrentUser(systemUserService.getById(0));
			}
			else {
				return "redirect:/login";
			}
		}
		return "/index";
	}

	@GetMapping("/login")
	public String login() {
		return "/user/login";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/login";
	}

	@GetMapping("/register")
	public String register() {
		return "/user/register";
	}

	@GetMapping("/home")
	public String home() {
		return "/home/index";
	}

}
