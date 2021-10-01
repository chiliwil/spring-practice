package com.proyecto.test.modules.user.service;

import com.proyecto.test.common.exception.ResourceNotFoundException;
import com.proyecto.test.modules.address.AddressEntity;
import com.proyecto.test.modules.address.AddressRepository;
import com.proyecto.test.modules.user.UserEntity;
import com.proyecto.test.modules.user.UserRepository;
import com.proyecto.test.modules.user.dto.CreateUserDto;
import com.proyecto.test.modules.user.dto.UpdateUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public UserEntity createUser(CreateUserDto user) throws Exception {
        Boolean existsUser = userRepository.existsByEmail(user.getEmail());

        if (existsUser) {
            throw new ResourceNotFoundException("User exist " + user.getEmail());
        }

        try {
            Optional<AddressEntity> address = addressRepository.findById(user.getAddressId());
            UserEntity createUser = new UserEntity();
            createUser.setEmail(user.getEmail());
            createUser.setUsername(user.getUsername());
            createUser.setGender(user.getGender());
            createUser.setAddress(address.get());
            userRepository.save(createUser);
            return createUser;
        } catch (DataAccessException ex) {
            throw new ResourceNotFoundException("User exist " + user.getEmail());
        }
    }

    @Override
    public UserEntity findByEmail(String email) throws ResourceNotFoundException {
        Boolean existsUser = userRepository.existsByEmail(email);
        if (existsUser) {
            throw new ResourceNotFoundException("User exist " + email);
        }
        return userRepository.findByEmail(email);
    }

    @Override
    public void deleteUser() {

    }

    @Override
    public Optional<UserEntity> updateUser(String id, UpdateUserDto userUpdate) throws ResourceNotFoundException {

        try {
            return userRepository.findById(UUID.fromString(id)).map(user -> {
                System.out.println("UPDATE" + user.toString());
                UserEntity updateUser = user;
                updateUser.setUsername(userUpdate.getUsername() != null ? userUpdate.getUsername() : user.getUsername());
                updateUser.setGender(userUpdate.getGender() != null ? userUpdate.getGender() : user.getGender());
                return userRepository.save(updateUser);
            });
        } catch (DataAccessException ex) {
            throw new ResourceNotFoundException("User exist " + userUpdate.getUsername());
        }
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findById(String id) throws ResourceNotFoundException {
        try {
            UUID uuid = UUID.fromString(id);
            Boolean existId = userRepository.existsById(uuid);
            if (!existId) {
                throw new ResourceNotFoundException("Not found ID " + id);
            }
            return userRepository.findById(uuid);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Not found ID " + id);
        }
    }
}
