package io.github.jaychoufans.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookInfoController {

	@GetMapping
	public String index() {
		return "book/index";
	}

	@GetMapping("/form")
	public String form() {
		return "book/form";
	}

	@GetMapping("/type")
	public String type() {
		return "/book/type";
	}

	@GetMapping("/lend")
	public String lend() {
		return "/book/lend";
	}

}
