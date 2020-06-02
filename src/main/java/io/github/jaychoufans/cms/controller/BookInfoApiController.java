package io.github.jaychoufans.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.jaychoufans.cms.annotation.RequiresPermission;
import io.github.jaychoufans.cms.common.ApiResponse;
import io.github.jaychoufans.cms.model.BookInfo;
import io.github.jaychoufans.cms.model.BookLend;
import io.github.jaychoufans.cms.model.BookType;
import io.github.jaychoufans.cms.model.SystemUser;
import io.github.jaychoufans.cms.model.view.BookInfoView;
import io.github.jaychoufans.cms.model.view.BookLendView;
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

	@PostMapping("/type")
	public ApiResponse<?> addType(BookType bookType) {
		bookTypeService.save(bookType);
		return ApiResponse.ok();
	}

	@GetMapping("/type/list")
	public ApiResponse<?> typeList() {
		return ApiResponse.ok(bookTypeService.list());
	}

	@RequiresPermission
	@PostMapping(name = "借阅图书", path = "/lend")
	public ApiResponse<?> lendBook(Long bookId) {
		bookService.lend(bookId);
		return ApiResponse.ok();
	}

	@RequiresPermission
	@GetMapping(name = "查看借阅列表", path = "/lend/list")
	public ApiResponse<?> lendList(long page, long limit) {
		Page<BookLend> pageBean = new Page<>(page, limit);
		bookLendService.page(pageBean);

		return ApiResponse.ok(pageBean, bookLend -> {
			BookLendView view = new BookLendView();
			BeanUtils.copyProperties(bookLend, view);

			SystemUser systemUser = systemUserService.getById(view.getUserId());
			view.setUserName(systemUser == null ? "" : systemUser.getTrueName());

			BookInfo bookInfo = bookInfoService.getById(view.getBookId());
			view.setBookName(bookInfo == null ? "" : bookInfo.getName());

			if (view.getState() == 0) {
				view.setStateName("待领取");
			}
			else if (view.getState() == 1) {
				view.setStateName("待归还");
			}
			else {
				view.setStateName("已归还");
			}

			return view;
		});
	}

}
