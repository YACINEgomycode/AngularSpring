package tn.kidzone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.kidzone.entity.ERole;
import tn.kidzone.entity.Role;
import tn.kidzone.entity.User;
import tn.kidzone.service.IUserService;

@CrossOrigin("*")
@RestController //staamlna hedhi moch controller bch get mapping tjib auto lhajet li tekhdem behom nab9ach nektebhom
@RequestMapping("/api/auth") //tetzed 9bal kol methode
public class UserController {
	

	@Autowired
	IUserService userService;
	
	@GetMapping("/afficherUsers")
	@ResponseBody //pour afficher resultat
	List<User> afficherUsers(){
	return userService.retrieveUsers();
	}

	@GetMapping("/afficherUserRoles")
	@ResponseBody //pour afficher resultat
	List<Role> afficherUserRoles(@PathVariable("username")String username){
		return userService.retrieveUserRoles(username);
	}


	@GetMapping("/afficherUser/{id}")
	@ResponseBody //pour afficher resultat
	User afficherUser(@PathVariable("id")Long id) {
	return userService.retrieveById(id);
	}
	
	@GetMapping("/afficherUser")
	@ResponseBody //pour afficher resultat
	User afficherUser2(@RequestParam("id")Long id) {
	return userService.retrieveById(id);
	}
	
	@PostMapping("/ajouterUser")
	@ResponseBody
	User ajouterUser(@RequestBody User u) { 
	return userService.saveUser(u);
	}
	
	@PutMapping("/updateUser")
	@ResponseBody
	User updateUser(@RequestBody User u) { 
	return userService.updateUser(u);
	}

	@PostMapping("/ajouterRoletoUser")
	@ResponseBody
	User ajouterRoletoUser(@RequestBody ERole role_name, @RequestBody String username) {

		User user=userService.addRoleToUser(username,role_name);
		return userService.saveUser(user);
	}


	@GetMapping("/afficherUserByName/{username}")
	@ResponseBody //pour afficher resultat
	User afficherUserByName(@PathVariable("username")String username) {
		return userService.getUserByUserName(username);
	}
	
	
	@DeleteMapping("/deleteUser/{id}")
	@ResponseBody
	void supprimerUser(@PathVariable("id") Long id) {
    userService.deleteUser(id);
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>>retrieveUsers() {
		return ResponseEntity.ok().body(userService.retrieveUsers());
	}

	public IUserService getUserService() {
		return userService;
	}


}
