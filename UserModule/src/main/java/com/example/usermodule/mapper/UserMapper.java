package com.example.usermodule.mapper;

import com.example.usermodule.model.UserEntity;
import com.example.util.entity.UserEntityDto;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserMapper {


    UserEntity toUserEntity(UserEntityDto dto);
}
