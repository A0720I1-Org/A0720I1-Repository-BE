package com.a0720i1.project_be.services.impl;

import com.a0720i1.project_be.models.Role;
import com.a0720i1.project_be.repositories.RoleRepository;
import com.a0720i1.project_be.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getById(int id) {
        return roleRepository.findById(id).orElse(null);
    }
}
