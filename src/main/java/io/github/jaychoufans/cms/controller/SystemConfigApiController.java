package io.github.jaychoufans.cms.controller;

import io.github.jaychoufans.cms.common.ApiResponse;
import io.github.jaychoufans.cms.model.SystemConfig;
import io.github.jaychoufans.cms.service.SystemConfigService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/system/config")
public class SystemConfigApiController {

	@Resource
	private SystemConfigService systemConfigService;

	@GetMapping("/map")
	public ApiResponse<?> map() {
		List<SystemConfig> list = systemConfigService.list();
		Map<String, String> data = list.stream()
				.collect(Collectors.toMap(SystemConfig::getConfigKey, SystemConfig::getConfigValue));
		return ApiResponse.ok(data);
	}

}
