package io.github.jaychoufans.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.jaychoufans.cms.common.ApiResponse;
import io.github.jaychoufans.cms.model.BookInfo;
import io.github.jaychoufans.cms.model.BookLend;
import io.github.jaychoufans.cms.model.BookType;
import io.github.jaychoufans.cms.service.BookInfoService;
import io.github.jaychoufans.cms.service.BookLendService;
import io.github.jaychoufans.cms.service.BookTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookInfoApiController {

	@Resource
	private BookInfoService bookInfoService;

	@Resource
	private BookTypeService bookTypeService;

	@Resource
	private BookLendService bookLendService;

	@PostMapping
	public ApiResponse<?> add(BookInfo bookInfo) {
		bookInfoService.save(bookInfo);
		return ApiResponse.ok();
	}

	@DeleteMapping
	public ApiResponse<?> del(@RequestBody List<Long> ids) {
		ids.forEach(id -> bookInfoService.removeById(id));
		return ApiResponse.ok();
	}

	@PutMapping
	public ApiResponse<?> mod(BookInfo bookInfo) {
		return ApiResponse.ok();
	}

	@GetMapping
	public ApiResponse<?> get() {
		return ApiResponse.ok();
	}

	@GetMapping("/list")
	public ApiResponse<?> list(long page, long limit) {
		Page<BookInfo> pageBean = new Page<>(page, limit);
		bookInfoService.page(pageBean);
		return ApiResponse.ok(pageBean);
	}

	@PostMapping("/type")
	public ApiResponse<?> addType(BookType bookType) {
		bookTypeService.save(bookType);
		return ApiResponse.ok();
	}

	@GetMapping("/type/list")
	public ApiResponse<?> typeList() {
		return ApiResponse.ok(bookTypeService.list());
	}

	@GetMapping("/lend/list")
	public ApiResponse<?> lendList(long page, long limit) {
		Page<BookLend> pageBean = new Page<>(page, limit);
		bookLendService.page(pageBean);
		return ApiResponse.ok(pageBean);
	}

}
