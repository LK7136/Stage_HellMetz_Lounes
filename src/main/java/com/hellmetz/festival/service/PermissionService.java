package com.hellmetz.festival.service;

import com.hellmetz.festival.model.Permission;
import com.hellmetz.festival.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public List<Permission> findAll() {return permissionRepository.findAll();}
}
