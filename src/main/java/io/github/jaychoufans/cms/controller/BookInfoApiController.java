package io.github.jaychoufans.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.jaychoufans.cms.common.ApiResponse;
import io.github.jaychoufans.cms.model.BookInfo;
import io.github.jaychoufans.cms.service.BookInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/v1/book")
public class BookInfoApiController {

	@Resource
	private BookInfoService bookInfoService;

	@GetMapping("/list")
	public ApiResponse<?> list(long page, long limit) {
		Page<BookInfo> pageBean = new Page<>(page, limit);
		bookInfoService.page(pageBean);
		return ApiResponse.ok(pageBean);
	}

}
