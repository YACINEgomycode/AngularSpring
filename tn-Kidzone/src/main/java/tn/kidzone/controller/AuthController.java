package tn.kidzone.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

import tn.kidzone.entity.ERole;
import tn.kidzone.entity.Role;
import tn.kidzone.entity.User;
import tn.kidzone.payload.request.LoginRequest;
import tn.kidzone.security.jwt.JwtUtils;
import tn.kidzone.security.services.UserDetailsImpl;
import tn.kidzone.security.services.UserDetailsServiceImpl;
import tn.kidzone.payload.request.LoginRequest;
import tn.kidzone.payload.request.SignupRequest;
import tn.kidzone.payload.response.JwtResponse;
import tn.kidzone.payload.response.MessageResponse;
import tn.kidzone.repository.RoleRepository;
import tn.kidzone.repository.UserRepository;




@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired 
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt, 
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(), 
                         roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    /*String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    if(fileName.contains(".."))
    {
      System.out.println("not a a valid file");
    }
    try {
      signUpRequest.setImage(Base64.getEncoder().encodeToString(file.getBytes()));

    } catch (IOException e) {
      e.printStackTrace();
    }*/
    User user = new User(
            signUpRequest.getFirstName(),
            signUpRequest.getLastName(),
            signUpRequest.getEmail(),
            signUpRequest.getUsername(),
            encoder.encode(signUpRequest.getPassword()),
            signUpRequest.getImage()








    );

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();
   // String firstname=signUpRequest.getFirstname();
   // String lastname=signUpRequest.getLastname();
    //String image=signUpRequest.getImage();

//ADMIN,PARENT,GOWNER,VISITOR,DOCTOR
    if (strRoles == null) {
      Role visitorRole = roleRepository.findByName(ERole.VISITOR);
      roles.add(visitorRole);

    }
    else{
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ADMIN);
             // .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "parent":
          Role parentRole = roleRepository.findByName(ERole.PARENT);
              //.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(parentRole);

          case "gowner":
            Role gownerRole = roleRepository.findByName(ERole.GOWNER);
            //.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(gownerRole);

          case "doctor":
            Role doctorRole = roleRepository.findByName(ERole.DOCTOR);
            //.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(doctorRole);

          break;
        default:
          Role visitorRole = roleRepository.findByName(ERole.VISITOR);
              //.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(visitorRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}
