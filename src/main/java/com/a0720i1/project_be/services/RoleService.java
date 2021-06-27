package com.a0720i1.project_be.services;

import com.a0720i1.project_be.models.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    List<Role> getAll();
    Role getById(int id);
}
