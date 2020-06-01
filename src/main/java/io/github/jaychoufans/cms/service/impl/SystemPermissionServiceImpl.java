package io.github.jaychoufans.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.jaychoufans.cms.mapper.SystemPermissionMapper;
import io.github.jaychoufans.cms.model.SystemPermission;
import io.github.jaychoufans.cms.service.SystemPermissionService;
import org.springframework.stereotype.Service;

@Service
public class SystemPermissionServiceImpl extends ServiceImpl<SystemPermissionMapper, SystemPermission>
		implements SystemPermissionService {

}
