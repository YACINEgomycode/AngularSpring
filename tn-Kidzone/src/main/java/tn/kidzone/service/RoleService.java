package tn.kidzone.service;

import lombok.extern.log4j.Log4j2;
import tn.kidzone.entity.ERole;
import tn.kidzone.entity.Role;
import tn.kidzone.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Log4j2

public class RoleService implements IRoleService {

    @Autowired
    RoleRepository roleRep;

    @Override
    public Role saveRole(Role role) {
        return roleRep.save(role);
    }

    @Override
    public Role retrieveById(Long id) {
        return roleRep.findById(id).orElse(null);

    }

    @Override
    public List<Role> retrieveRoles() {

        return (List<Role>) roleRep.findAll();


    }

    @Override
    public Role getRoleByName(ERole role_name) {

        return  roleRep.findByName(role_name);
    }

    @Override
    public Role updateRole(Role role) {
        return roleRep.save(role);


    }

    @Override
    public void deleteROLE(Long id) {
        roleRep.deleteById(id);

    }
}
