package io.github.jaychoufans.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.jaychoufans.cms.mapper.SystemConfigMapper;
import io.github.jaychoufans.cms.model.SystemConfig;
import io.github.jaychoufans.cms.service.SystemConfigService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
public class SystemConfigServiceImpl extends ServiceImpl<SystemConfigMapper, SystemConfig>
		implements SystemConfigService {

	@Resource
	private SystemConfigService that;

	@Override
	public SystemConfig getByKey(String key) {
		if (StringUtils.isEmpty(key)) {
			return null;
		}

		SystemConfig args = new SystemConfig();
		args.setConfigKey(key);
		return that.list(new QueryWrapper<>(args)).stream().findFirst().orElse(null);
	}

}
