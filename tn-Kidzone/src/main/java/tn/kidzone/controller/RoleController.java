package tn.kidzone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tn.kidzone.entity.ERole;
import tn.kidzone.entity.Role;
import tn.kidzone.service.IRoleService;

import java.util.List;

@RestController //staamlna hedhi moch controller bch get mapping tjib auto lhajet li tekhdem behom nab9ach nektebhom
@RequestMapping("RoleController") //tetzed 9bal kol methode

public class RoleController {
    @Autowired
    IRoleService roleService;

    @GetMapping("/afficherRoles")
    @ResponseBody
        //pour afficher resultat
    List<Role> afficherRoles(){
        return roleService.retrieveRoles();
    }

    @GetMapping("/afficherRoleByName/{name}")
    @ResponseBody //pour afficher resultat
    Role afficherRoleByName(@PathVariable("name") ERole name) {
        return roleService.getRoleByName(name);
    }



    @GetMapping("/afficherRole/{id}")
    @ResponseBody //pour afficher resultat
    Role afficherRole(@PathVariable("id")Long id) {
        return roleService.retrieveById(id);
    }



    @PostMapping("/ajouterRole")
    @ResponseBody
    Role ajouterRole(@RequestBody Role role) {
        return roleService.saveRole(role);
    }

    @PutMapping("/updateRole")
    @ResponseBody
    Role updateRole(@RequestBody Role role) {
        return roleService.updateRole(role);
    }

    @DeleteMapping("/deleteRole/{id}")
    @ResponseBody
    void supprimerRole(@PathVariable("id") Long id) {
        roleService.deleteROLE(id);
    }

}
