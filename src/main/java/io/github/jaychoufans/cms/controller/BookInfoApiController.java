package io.github.jaychoufans.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.jaychoufans.cms.annotation.RequiresPermission;
import io.github.jaychoufans.cms.common.ApiResponse;
import io.github.jaychoufans.cms.model.BookInfo;
import io.github.jaychoufans.cms.model.BookType;
import io.github.jaychoufans.cms.model.view.BookInfoView;
import io.github.jaychoufans.cms.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static io.github.jaychoufans.cms.common.Const.QUERY_PAGE_STRING;
import static io.github.jaychoufans.cms.common.Const.QUERY_LIMIT_STRING;

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

	/**
	 * 查询图书列表
	 * @param isbn ISBN
	 * @param name 书名，该字段模糊搜索
	 * @param author 作者
	 * @param page 页码
	 * @param limit 请求记录数
	 * @return 图书列表
	 */
	@RequiresPermission
	@GetMapping(name = "查看图书列表", path = "/list")
	public ApiResponse<?> list(@RequestParam(required = false) String isbn, @RequestParam(required = false) String name,
			@RequestParam(required = false) String author,
			@RequestParam(required = false, defaultValue = QUERY_PAGE_STRING) long page,
			@RequestParam(required = false, defaultValue = QUERY_LIMIT_STRING) long limit) {

		boolean wrapper = false;

		BookInfo entity = new BookInfo();
		QueryWrapper<BookInfo> queryWrapper = new QueryWrapper<>(entity);
		Page<BookInfo> pageBean = new Page<>(page, limit);

		if (StringUtils.isNotBlank(isbn)) {
			entity.setIsbn(isbn);
			wrapper = true;
		}
		if (StringUtils.isNotBlank(name)) {
			queryWrapper.like(BookInfo.NAME, name);
			wrapper = true;
		}
		if (StringUtils.isNotBlank(author)) {
			entity.setAuthor(author);
			wrapper = true;
		}

		if (wrapper) {
			bookInfoService.page(pageBean, queryWrapper);
		}
		else {
			bookInfoService.page(pageBean);
		}

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
