package io.github.jaychoufans.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.jaychoufans.cms.mapper.SystemMenuMapper;
import io.github.jaychoufans.cms.model.SystemMenu;
import io.github.jaychoufans.cms.service.SystemMenuService;
import org.springframework.stereotype.Service;

@Service
public class SystemMenuServiceImpl extends ServiceImpl<SystemMenuMapper, SystemMenu> implements SystemMenuService {

}
