package com.site.user;

import com.site.util.BaseMapper;
import org.springframework.beans.BeanUtils;

public class UserMapper extends BaseMapper<User, UserDto> {
    @Override
    public User convertToEntity(UserDto dto, Object... args) {
       User user = new User();
       if (dto != null)
           BeanUtils.copyProperties(dto, user);
       return user;
    }

    @Override
    public UserDto convertToDto(User entity, Object... args) {
        UserDto dto = new UserDto();
        if (entity != null)
            BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
