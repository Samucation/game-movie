package com.evaluation.movie.battle.builder;

import com.evaluation.movie.battle.dto.UserDTO;
import com.evaluation.movie.battle.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public UserDTO convert(User user){
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    public User convert(UserDTO userDTO){
        User user = modelMapper.map(userDTO, User.class);
        return user;
    }

    public List<User> convertEntityList(List<UserDTO> corporationDTOList){
        return corporationDTOList.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public List<UserDTO> convertDTOList(List<User> corporationList){
        return corporationList.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

}
