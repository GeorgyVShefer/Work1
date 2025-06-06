package com.example.usermodule.service;


import com.example.usermodule.mapper.UserMapper;
import com.example.usermodule.model.UserEntity;
import com.example.usermodule.repository.UserRepository;
import com.example.util.entity.UserEntityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserEntity save(UserEntityDto dto){

        UserEntity userEntity = userMapper.toUserEntity(dto);

        return userRepository.save(userEntity);
    }
}
