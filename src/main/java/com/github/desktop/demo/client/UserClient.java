package com.github.desktop.demo.client;

import com.github.desktop.demo.dto.ResponseUserDTO;

public interface UserClient {

    ResponseUserDTO fetchUser(Integer page, Integer size);
}
