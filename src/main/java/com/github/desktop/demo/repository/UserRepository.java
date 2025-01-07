package com.github.desktop.demo.repository;

import com.asliri.core.applications.rest.annotations.Get;
import com.asliri.core.applications.rest.repository.RestRepository;
import com.github.desktop.demo.dto.ResponseUserDTO;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserRepository extends RestRepository {

    @Get(key = "${app.user.random.service}", url = "/api/1.4")
    ResponseUserDTO fetchUserAll(
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "results") Integer size,
            @RequestParam(name = "seed") String seed,
            @RequestParam(name = "exc") String exclude
    );
}
