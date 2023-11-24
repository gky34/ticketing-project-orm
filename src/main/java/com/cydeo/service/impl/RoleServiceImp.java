package com.cydeo.service.impl;

import com.cydeo.dto.RoleDTO;
import com.cydeo.entity.Role;
import com.cydeo.mapper.RoleMapper;
import com.cydeo.repository.RoleRepository;
import com.cydeo.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RoleServiceImp implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImp(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleDTO> listAllRoles() {

        // bring all the roles
        List<Role> roleList = roleRepository.findAll();

        // convert entity to dto - MAPPER
        // get roles from db and convert each role to roledto
        List<RoleDTO> list2 = roleList.stream().map(roleMapper::convertToDto).collect(Collectors.toList());

        return list2;
    }

    @Override
    public RoleDTO findById(Long id) {

        return roleMapper.convertToDto(roleRepository.findById(id).get());
    }
}
