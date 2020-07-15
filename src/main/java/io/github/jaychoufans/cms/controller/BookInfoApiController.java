package io.github.jaychoufans.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.jaychoufans.cms.annotation.RequiresPermission;
import io.github.jaychoufans.cms.common.ApiResponse;
import io.github.jaychoufans.cms.model.BookInfo;
import io.github.jaychoufans.cms.model.BookType;
import io.github.jaychoufans.cms.model.view.BookInfoView;
import io.github.jaychoufans.cms.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookInfoApiController {

	@Resource
	private BookService bookService;

	@Resource
	private BookInfoService bookInfoService;

	@Resource
	private BookTypeService bookTypeService;

	@Resource
	private BookLendService bookLendService;

	@Resource
	private SystemUserService systemUserService;

	@PostMapping
	@RequiresPermission
	public ApiResponse<?> add(BookInfo bookInfo) {
		bookInfoService.save(bookInfo);
		return ApiResponse.ok();
	}

	@DeleteMapping
	@RequiresPermission
	public ApiResponse<?> del(@RequestBody List<Long> ids) {
		ids.forEach(id -> bookInfoService.removeById(id));
		return ApiResponse.ok();
	}

	@PutMapping
	@RequiresPermission
	public ApiResponse<?> mod(@RequestBody BookInfo args) {
		bookInfoService.updateById(args);
		return ApiResponse.ok();
	}

	@GetMapping
	public ApiResponse<?> get() {
		return ApiResponse.ok();
	}

	@RequiresPermission
	@GetMapping(name = "查看图书列表", path = "/list")
	public ApiResponse<?> list(long page, long limit) {
		Page<BookInfo> pageBean = new Page<>(page, limit);
		bookInfoService.page(pageBean);

		return ApiResponse.ok(pageBean, book -> {
			BookInfoView view = new BookInfoView();
			BeanUtils.copyProperties(book, view);

			BookType bookType = bookTypeService.getById(view.getType());
			view.setTypeName(bookType == null ? "" : bookType.getName());

			return view;
		});
	}

	@RequiresPermission
	@PostMapping("/type")
	public ApiResponse<?> addType(BookType bookType) {
		bookTypeService.save(bookType);
		return ApiResponse.ok();
	}

	@RequiresPermission
	@DeleteMapping("/type")
	public ApiResponse<?> delType(@RequestBody List<Long> ids) {
		ids.forEach(id -> bookTypeService.removeById(id));
		return ApiResponse.ok();
	}

	@RequiresPermission
	@PutMapping("/type")
	public ApiResponse<?> modType(@RequestBody BookType args) {
		bookTypeService.updateById(args);
		return ApiResponse.ok();
	}

	@GetMapping("/type/list")
	public ApiResponse<?> typeList() {
		return ApiResponse.ok(bookTypeService.list());
	}

}
