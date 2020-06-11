package io.github.jaychoufans.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.jaychoufans.cms.exception.CmsException;
import io.github.jaychoufans.cms.mapper.SystemUserMapper;
import io.github.jaychoufans.cms.model.*;
import io.github.jaychoufans.cms.service.*;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements SystemUserService {

	@Resource
	private SystemRoleService systemRoleService;

	@Resource
	private SystemUserRoleService systemUserRoleService;

	@Resource
	private SystemPermissionService systemPermissionService;

	@Resource
	private SystemRolePermissionService systemRolePermissionService;

	@Override
	@Transactional
	public boolean save(SystemUser entity) {
		// 保存用户信息
		boolean save;
		try {
			save = super.save(entity);
		}
		catch (DuplicateKeyException e) {
			CmsException cmsException = new CmsException("A0111", "用户名已存在");
			cmsException.initCause(e);
			throw cmsException;
		}
		// 查询默认角色
		List<Long> roleIds = systemRoleService.list().stream().filter(SystemRole::getDefaultRole).map(SystemRole::getId)
				.collect(Collectors.toList());
		// 赋予默认角色
		this.setRole(entity.getId(), roleIds);
		return save;
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

	@Override
	@Transactional
	public void setRole(Long userId, List<Long> roleIds) {
		// 删除原有角色
		SystemUserRole entity = new SystemUserRole();
		entity.setUserId(userId);
		systemUserRoleService.remove(new QueryWrapper<>(entity));
		// 插入新增角色
		if (CollectionUtils.isNotEmpty(roleIds)) {
			List<SystemUserRole> entityList = roleIds.stream().map(roleId -> new SystemUserRole(userId, roleId))
					.collect(Collectors.toList());
			systemUserRoleService.saveBatch(entityList);
		}
	}

}
