package io.github.jaychoufans.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.jaychoufans.cms.annotation.RequiresPermission;
import io.github.jaychoufans.cms.common.ApiResponse;
import io.github.jaychoufans.cms.model.Documentation;
import io.github.jaychoufans.cms.model.DocumentationType;
import io.github.jaychoufans.cms.model.view.DocumentationInfoView;
import io.github.jaychoufans.cms.service.DocumentationService;
import io.github.jaychoufans.cms.service.DocumentationTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/v1/documentation")
public class DocumentationApiController {

	@Resource
	private DocumentationService documentationService;

	@Resource
	private DocumentationTypeService documentationTypeService;

	@PostMapping
	@RequiresPermission
	public ApiResponse<?> add(Documentation documentation) {
		documentationService.save(documentation);
		return ApiResponse.ok();
	}

	@DeleteMapping
	@RequiresPermission
	public ApiResponse<?> del(@RequestBody List<Long> ids) {
		ids.forEach(id -> documentationService.removeById(id));
		return ApiResponse.ok();
	}

	@PutMapping
	@RequiresPermission
	public ApiResponse<?> mod(@RequestBody Documentation args) {
		documentationService.updateById(args);
		return ApiResponse.ok();
	}

	@RequiresPermission
	@GetMapping(name = "查看资料列表", path = "/list")
	public ApiResponse<?> list(long page, long limit) {
		Page<Documentation> pageBean = new Page<>(page, limit);
		documentationService.page(pageBean);
		return ApiResponse.ok(pageBean, documentation -> {
			DocumentationInfoView view = new DocumentationInfoView();
			BeanUtils.copyProperties(documentation, view);

			DocumentationType documentationType = documentationTypeService.getById(view.getType());
			view.setTypeName(documentationType == null ? "" : documentationType.getName());

			return view;
		});
	}

	@RequiresPermission
	@PostMapping("/type")
	public ApiResponse<?> addType(DocumentationType args) {
		documentationTypeService.save(args);
		return ApiResponse.ok();
	}

	@RequiresPermission
	@DeleteMapping("/type")
	public ApiResponse<?> delType(@RequestBody List<Long> ids) {
		ids.forEach(id -> documentationTypeService.removeById(id));
		return ApiResponse.ok();
	}

	@RequiresPermission
	@PutMapping("/type")
	public ApiResponse<?> modType(@RequestBody DocumentationType args) {
		documentationTypeService.updateById(args);
		return ApiResponse.ok();
	}

	@GetMapping("/type/list")
	public ApiResponse<?> listType() {
		return ApiResponse.ok(documentationTypeService.list());
	}

}
