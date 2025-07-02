package com.example.usermodule.service;


import com.example.usermodule.mapper.UserMapper;
import com.example.usermodule.model.UserEntity;
import com.example.usermodule.repository.UserRepository;
import com.example.util.entity.UserEntityDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserEntity save(UserEntityDto dto){

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(dto.getEmail());
        userEntity.setOperation(dto.getOperation());
        return userRepository.save(userEntity);
    }

    private final CacheManager cacheManager;

    @Cacheable(value = "users", key = "#id")
    public UserEntityDto getById(Long id){

        Cache cache = cacheManager.getCache("users");

        cache.get(id);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        UserEntityDto userEntityDto = new UserEntityDto();
        userEntityDto.setEmail(userEntity.getEmail());
        userEntityDto.setOperation(userEntity.getOperation());

        return userEntityDto;
    }

    @CachePut(value = "users", key = "#id")
    public UserEntity update(UserEntityDto dto, Long id){

        UserEntity userEntity = userMapper.toUserEntity(dto);

        return userRepository.save(userEntity);
    }

    @CacheEvict(value = "users", key = "#id")
    public void deleteById(Long id){


        userRepository.deleteById(id);
    }
}
