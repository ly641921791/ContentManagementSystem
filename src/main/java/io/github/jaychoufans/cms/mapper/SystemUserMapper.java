package io.github.jaychoufans.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.jaychoufans.cms.model.SystemPermission;
import io.github.jaychoufans.cms.model.SystemUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SystemUserMapper extends BaseMapper<SystemUser> {

	@Select("SELECT sp.* FROM system_user_role AS sur "
			+ "INNER JOIN system_role_permission AS srp ON sur.role_id = srp.role_id "
			+ "INNER JOIN system_permission AS sp ON srp.permission_id = sp.id " + "WHERE sur.user_id = #{id}")
	List<SystemPermission> getPermissionById(Long id);

}
