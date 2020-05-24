package io.github.jaychoufans.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookInfoController {

	@GetMapping("/list")
	public String list() {
		return "book/list";
	}

	@GetMapping("/form")
	public String form() {
		return "book/form";
	}

}
