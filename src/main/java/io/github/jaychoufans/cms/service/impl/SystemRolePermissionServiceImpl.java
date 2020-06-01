package io.github.jaychoufans.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.jaychoufans.cms.mapper.SystemRolePermissionMapper;
import io.github.jaychoufans.cms.model.SystemRolePermission;
import io.github.jaychoufans.cms.service.SystemRolePermissionService;
import org.springframework.stereotype.Service;

@Service
public class SystemRolePermissionServiceImpl extends ServiceImpl<SystemRolePermissionMapper, SystemRolePermission>
		implements SystemRolePermissionService {

}
