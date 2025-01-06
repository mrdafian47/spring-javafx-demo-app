package com.github.desktop.demo.service;

import org.springframework.stereotype.Service;

@Service
public class RandomServiceImpl implements RandomService {

    @Override
    public String getRandomWord() {
        return "Hello, you see this text right?";
    }
}
