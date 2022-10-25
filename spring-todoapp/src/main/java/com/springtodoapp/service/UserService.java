package com.springtodoapp.service;

import com.springtodoapp.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.springtodoapp.request.UserRegistrationRequest;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationRequest userRegistrationRequest);



}
