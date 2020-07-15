package io.github.jaychoufans.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.jaychoufans.cms.model.SystemConfig;

public interface SystemConfigService extends IService<SystemConfig> {

	/**
	 * 根据 key 获取 配置
	 * @param key key
	 * @return 配置
	 */
	SystemConfig getByKey(String key);

}
