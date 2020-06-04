package io.github.jaychoufans.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.jaychoufans.cms.exception.CmsException;
import io.github.jaychoufans.cms.mapper.SystemUserMapper;
import io.github.jaychoufans.cms.model.SystemPermission;
import io.github.jaychoufans.cms.model.SystemRolePermission;
import io.github.jaychoufans.cms.model.SystemUser;
import io.github.jaychoufans.cms.model.SystemUserRole;
import io.github.jaychoufans.cms.service.SystemPermissionService;
import io.github.jaychoufans.cms.service.SystemRolePermissionService;
import io.github.jaychoufans.cms.service.SystemUserRoleService;
import io.github.jaychoufans.cms.service.SystemUserService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements SystemUserService {

	@Resource
	private SystemUserRoleService systemUserRoleService;

	@Resource
	private SystemPermissionService systemPermissionService;

	@Resource
	private SystemRolePermissionService systemRolePermissionService;

	@Override
	public boolean save(SystemUser entity) {
		try {
			return super.save(entity);
		}
		catch (DuplicateKeyException e) {
			CmsException cmsException = new CmsException("A0111", "用户名已存在");
			cmsException.initCause(e);
			throw cmsException;
		}
	}

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
		SystemUserRole userRole = new SystemUserRole();
		userRole.setUserId(id);

		return systemUserRoleService.list(new QueryWrapper<>(userRole)).stream()
				// userRole => rolePermission
				.map(ur -> {
					SystemRolePermission rolePermission = new SystemRolePermission();
					rolePermission.setRoleId(ur.getRoleId());
					return systemRolePermissionService.list(new QueryWrapper<>(rolePermission));
				}).flatMap(Collection::stream)
				// rolePermission => permission
				.map(SystemRolePermission::getPermissionId).distinct()
				.map(permissionId -> systemPermissionService.getById(permissionId)).collect(Collectors.toList());
	}

}
