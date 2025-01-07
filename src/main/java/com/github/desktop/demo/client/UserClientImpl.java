package com.github.desktop.demo.client;

import com.github.desktop.demo.dto.ResponseUserDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Log4j2
@Service
public class UserClientImpl implements UserClient {

    @Override
    public ResponseUserDTO fetchUser(Integer page, Integer size) {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "https://randomuser.me/api/1.4/?page=1&results=10&seed=asli&exc=login,dob,registered";
        ResponseEntity<ResponseUserDTO> response = restTemplate.getForEntity(fooResourceUrl, ResponseUserDTO.class);
        return response.getBody();
    }
}
