package io.github.jaychoufans.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/documentation")
public class DocumentController {

	@GetMapping
	public String list() {
		return "documentation/index";
	}

}
