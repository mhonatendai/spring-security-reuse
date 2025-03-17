package com.tenstech.spring_security_reuse.typemapper;

import com.tenstech.spring_security_reuse.auth.AuthDTO;
import com.tenstech.spring_security_reuse.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(componentModel = "spring")
public interface TypeMapper {

    User toUser(AuthDTO.UserRequest userRequest);

}