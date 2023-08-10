package com.example.ankaref.Business.concret;

import com.example.ankaref.Business.Abstracts.RolService;
import com.example.ankaref.DataAccess.RoleRepository;
import com.example.ankaref.Entities.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public void creatRequest(Role creatRequest) {
        roleRepository.save(creatRequest);
        log.info("create rols");
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
