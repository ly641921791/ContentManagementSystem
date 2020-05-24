package io.github.jaychoufans.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.jaychoufans.cms.common.ApiResponse;
import io.github.jaychoufans.cms.model.Documentation;
import io.github.jaychoufans.cms.service.DocumentationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/v1/documentation")
public class DocumentationApiController {

	@Resource
	private DocumentationService documentationService;

	@GetMapping("/list")
	public ApiResponse<?> list(long page, long limit) {
		Page<Documentation> pageBean = new Page<>(page, limit);
		documentationService.page(pageBean);
		return ApiResponse.ok(pageBean);
	}

}
