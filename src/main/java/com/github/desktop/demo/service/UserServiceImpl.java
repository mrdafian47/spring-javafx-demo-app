package com.github.desktop.demo.service;

import com.github.desktop.demo.client.UserClient;
import com.github.desktop.demo.dto.UserDTO;
import com.github.desktop.demo.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserClient client;

    @Override
    public List<UserDTO> fetchUserAll(Integer page, Integer size) {
//        return repository.fetchUserAll(
//                page,
//                size,
//                "asli",
//                "login,dob,registered"
//        ).getUserDTOList();
        return client.fetchUser(page, size).getUserDTOList();
    }
}
