package tn.kidzone.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

  @GetMapping("/visitor")
  @PreAuthorize("hasRole('Visitor')")
  public String userAccess() {
    return "Visitor Content.";
  }

  @GetMapping("/parent")
  @PreAuthorize("hasRole('PARENT')")
  public String parentAccess() {
    return "Parent Board.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    return "Admin Board.";
  }

  @GetMapping("/gowner")
  @PreAuthorize("hasRole('GOWNER')")
  public String gownerAccess() {
    return "Kidzone Owner Board.";
  }
}
