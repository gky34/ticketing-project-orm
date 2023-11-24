package com.cydeo.service.impl;

import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.mapper.UserMapper;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImp(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> listAllUser() {

        List<User> userList = userRepository.findAll(Sort.by("firstName"));

        return userList.stream().map(userMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) {

        User user = userRepository.findByUserName(username);
        return userMapper.convertToDTO(user);
    }

    @Override
    public void save(UserDTO dto) {

        userRepository.save(userMapper.convertToEntity(dto));
    }

    @Override
    public UserDTO update(UserDTO dto) {

        // Find a current user
        User user = userRepository.findByUserName(dto.getUserName());
        // Map updated user dto to entity object
        User convertedUser = userMapper.convertToEntity(dto);

        // set id to converted object
        convertedUser.setId(user.getId());
        userRepository.save(convertedUser);
        return findByUserName(dto.getUserName());
    }

    @Override
    public void deleteByUsername(String username) {

       userRepository.deleteByUserName(username);

    }

    @Override
    public void delete(String username) {
        //change the flag and keep it in the db
        User user = userRepository.findByUserName(username);
        user.setIsDeleted(true);
        userRepository.save(user);

    }

    @Override
    public List<UserDTO> listAllByRole(String role) {
        List<User> userDTOList = userRepository.findAllByRoleDescriptionIgnoreCase(role);

        return userDTOList.stream().map(userMapper::convertToDTO).collect(Collectors.toList());
    }
}

