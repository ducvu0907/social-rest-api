package com.dev.SocialMedia.service;

import com.dev.SocialMedia.dto.UserDto;
import com.dev.SocialMedia.dto.UserLoginDto;
import com.dev.SocialMedia.dto.UserRegisterDto;

import java.util.List;

public interface IUserService {

    UserDto register(UserRegisterDto dto);

    UserDto login(UserLoginDto dto);

    void logout();

}
