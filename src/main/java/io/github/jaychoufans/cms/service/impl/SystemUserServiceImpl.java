package io.github.jaychoufans.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.jaychoufans.cms.mapper.SystemUserMapper;
import io.github.jaychoufans.cms.model.SystemUser;
import io.github.jaychoufans.cms.service.SystemUserService;
import org.springframework.stereotype.Service;

@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements SystemUserService {

	@Override
	public SystemUser findByUsername(String username) {
		return getBaseMapper().findByUsername(username);
	}

}
