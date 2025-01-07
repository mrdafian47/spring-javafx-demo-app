package com.github.desktop.demo.service;

import com.github.desktop.demo.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> fetchUserAll(Integer page, Integer size);
}
