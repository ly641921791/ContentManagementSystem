package io.github.jaychoufans.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system")
public class SystemController {

	@GetMapping("/role")
	public String role() {
		return "/role/index";
	}

}
