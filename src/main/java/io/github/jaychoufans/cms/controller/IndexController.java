package io.github.jaychoufans.cms.controller;

import io.github.jaychoufans.cms.utils.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

	@GetMapping("/")
	public String index() {
		if (WebUtils.getCurrentUser() == null) {
			return "redirect:/login";
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
