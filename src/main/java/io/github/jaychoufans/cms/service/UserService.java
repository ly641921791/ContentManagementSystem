package io.github.jaychoufans.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.jaychoufans.cms.model.User;

public interface UserService extends IService<User> {

	User findByUsername(String username);

}
