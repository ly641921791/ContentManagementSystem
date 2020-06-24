package io.github.jaychoufans.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/documentation")
public class DocumentController {

	@GetMapping
	public String index() {
		return "/documentation/index";
	}

	@GetMapping("/type")
	public String type() {
		return "/documentation/type";
	}

	@GetMapping("/lend")
	public String lend() {
		return "/documentation/lend";
	}

}
