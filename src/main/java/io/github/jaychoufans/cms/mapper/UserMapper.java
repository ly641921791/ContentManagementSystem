package io.github.jaychoufans.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.jaychoufans.cms.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {

	@Select("SELECT * FROM user WHERE username = #{username}")
	User findByUsername(String username);

}
