package io.github.jaychoufans.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.jaychoufans.cms.mapper.SystemUserRoleMapper;
import io.github.jaychoufans.cms.model.SystemUserRole;
import io.github.jaychoufans.cms.service.SystemUserRoleService;
import org.springframework.stereotype.Service;

@Service
public class SystemUserRoleServiceImpl extends ServiceImpl<SystemUserRoleMapper, SystemUserRole>
		implements SystemUserRoleService {

}
