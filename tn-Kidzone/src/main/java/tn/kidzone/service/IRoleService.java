package tn.kidzone.service;



import java.util.List;

import tn.kidzone.entity.ERole;
import tn.kidzone.entity.Role;

public interface IRoleService {
    Role saveRole(Role role);
    Role retrieveById(Long id);

    List<Role> retrieveRoles();
    Role getRoleByName(ERole role_name);
    Role updateRole(Role role);
    void deleteROLE(Long id);


}
