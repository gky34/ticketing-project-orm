package com.cydeo.converter;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.UserService;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class UserDtoConverter implements Converter<String, UserDTO> {

    UserService userService;

    public UserDtoConverter(@Lazy UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDTO convert(String source) {
        return userService.findByUserName(source);
    }

}
// Why we are using Converter Interface
//  Because we're getting string from UI and We need an object so that's the reason
