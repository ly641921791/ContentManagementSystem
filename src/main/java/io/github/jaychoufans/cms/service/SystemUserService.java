package io.github.jaychoufans.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.jaychoufans.cms.model.SystemUser;

public interface SystemUserService extends IService<SystemUser> {

	SystemUser findByUsername(String username);

}
