package com.proyecto.test.modules.user.service;

import com.proyecto.test.common.exception.ResourceNotFoundException;
import com.proyecto.test.modules.user.UserEntity;
import com.proyecto.test.modules.user.dto.CreateUserDto;
import com.proyecto.test.modules.user.dto.UpdateUserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserEntity createUser(CreateUserDto user) throws Exception;

    UserEntity findByEmail(String email) throws ResourceNotFoundException;

    void deleteUser();

    Optional<UserEntity> updateUser(String id, UpdateUserDto user) throws Exception;

    List<UserEntity> getAllUsers();

    Optional<UserEntity> findById(String id) throws ResourceNotFoundException;
}
