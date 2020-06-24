package io.github.jaychoufans.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.jaychoufans.cms.annotation.RequiresPermission;
import io.github.jaychoufans.cms.common.ApiResponse;
import io.github.jaychoufans.cms.model.*;
import io.github.jaychoufans.cms.model.view.DocumentationLendView;
import io.github.jaychoufans.cms.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/v1/documentation/lend")
public class DocumentationLendApiController {

	@Resource
	private DocumentationService documentationService;

	@Resource
	private DocumentationLendService documentationLendService;

	@Resource
	private SystemUserService systemUserService;

	@RequiresPermission
	@PostMapping(name = "借阅图书")
	public ApiResponse<?> lendBook(Long documentationId) {
		documentationService.lend(documentationId);
		return ApiResponse.ok();
	}

	@RequiresPermission
	@PutMapping(name = "借还操作")
	public ApiResponse<?> modLendStatus(@RequestBody DocumentationLend args) {
		documentationService.modLendStatus(args);
		return ApiResponse.ok();
	}

	@RequiresPermission
	@GetMapping(name = "查看借阅列表", path = "/list")
	public ApiResponse<?> lendList(long page, long limit) {
		Page<DocumentationLend> pageBean = new Page<>(page, limit);
		documentationLendService.page(pageBean);

		return ApiResponse.ok(pageBean, documentationLend -> {
			DocumentationLendView view = new DocumentationLendView();
			BeanUtils.copyProperties(documentationLend, view);

			SystemUser systemUser = systemUserService.getById(view.getUserId());
			view.setUserName(systemUser == null ? "" : systemUser.getTrueName());

			Documentation documentation = documentationService.getById(view.getDocumentationId());
			view.setDocumentationName(documentation == null ? "" : documentation.getName());

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
