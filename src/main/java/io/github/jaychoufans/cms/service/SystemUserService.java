package io.github.jaychoufans.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.jaychoufans.cms.model.SystemPermission;
import io.github.jaychoufans.cms.model.SystemUser;

import java.util.List;

public interface SystemUserService extends IService<SystemUser> {

	SystemUser findByUsername(String username);

	@Deprecated
	List<SystemPermission> getPermissionById(Long id);

}
