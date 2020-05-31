package io.github.jaychoufans.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.jaychoufans.cms.model.SystemUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SystemUserMapper extends BaseMapper<SystemUser> {

	@Select("SELECT * FROM user WHERE username = #{username}")
	SystemUser findByUsername(String username);

}
