package io.github.jaychoufans.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.jaychoufans.cms.mapper.SystemUserMapper;
import io.github.jaychoufans.cms.model.SystemPermission;
import io.github.jaychoufans.cms.model.SystemUser;
import io.github.jaychoufans.cms.service.SystemUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements SystemUserService {

	@Override
	public SystemUser findByUsername(String username) {
		SystemUser entity = new SystemUser();
		entity.setUsername(username);
		List<SystemUser> list = this.list(new QueryWrapper<>(entity));
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<SystemPermission> getPermissionById(Long id) {
		return getBaseMapper().getPermissionById(id);
	}

}
