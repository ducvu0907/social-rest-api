package com.dev.SocialMedia.service.impl;

import com.dev.SocialMedia.repository.UserRepository;
import com.dev.SocialMedia.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;


}