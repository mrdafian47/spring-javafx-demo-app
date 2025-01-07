package com.github.desktop.demo.model;

import com.github.desktop.demo.dto.UserDTO;
import com.github.desktop.demo.dto.UserNameDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserModel {
    private String name;
    private String email;

    public static UserModel create(UserDTO userDTO) {
        UserNameDTO nameDTO = userDTO.getNameDTO();
        return UserModel.builder()
                .name(nameDTO.getTitle() + ". " + nameDTO.getFirst() + " " + nameDTO.getLast())
                .email(userDTO.getEmail())
                .build();
    }
}
