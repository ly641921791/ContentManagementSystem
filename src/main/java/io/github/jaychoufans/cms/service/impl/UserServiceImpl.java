package io.github.jaychoufans.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.jaychoufans.cms.mapper.UserMapper;
import io.github.jaychoufans.cms.model.User;
import io.github.jaychoufans.cms.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Override
	public User findByUsername(String username) {
		return getBaseMapper().findByUsername(username);
	}

}
