package io.github.jaychoufans.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.jaychoufans.cms.annotation.RequiresPermission;
import io.github.jaychoufans.cms.common.ApiResponse;
import io.github.jaychoufans.cms.model.BookInfo;
import io.github.jaychoufans.cms.model.BookLend;
import io.github.jaychoufans.cms.model.SystemUser;
import io.github.jaychoufans.cms.model.view.BookLendView;
import io.github.jaychoufans.cms.service.BookInfoService;
import io.github.jaychoufans.cms.service.BookLendService;
import io.github.jaychoufans.cms.service.BookService;
import io.github.jaychoufans.cms.service.SystemUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/v1/book/lend")
public class BookLendApiController {

	@Resource
	private BookService bookService;

	@Resource
	private BookInfoService bookInfoService;

	@Resource
	private BookLendService bookLendService;

	@Resource
	private SystemUserService systemUserService;

	@PostMapping(name = "借阅图书")
	public ApiResponse<?> lendBook(Long bookId) {
		bookService.lend(bookId);
		return ApiResponse.ok();
	}

	@RequiresPermission
	@PutMapping(name = "借还操作")
	public ApiResponse<?> modLendStatus(@RequestBody BookLend args) {
		bookService.modLendStatus(args);
		return ApiResponse.ok();
	}

	@RequiresPermission
	@GetMapping(name = "查看借阅列表", path = "/list")
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
