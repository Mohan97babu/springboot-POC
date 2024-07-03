package com.school.test.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.test.dto.LoginRequest;
import com.school.test.dto.SignupRequest;
import com.school.test.dto.UserInfoResponse;
import com.school.test.service.AuthService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
  
	
	
  @Autowired
 private  AuthService authservice;

  @PostMapping("/signin/user")
  @PostAuthorize("hasRole('USER')")
  public UserInfoResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
	 
	  return this.authservice.authenticateUser(loginRequest, response);
  }
  
  @PostMapping("/signin/admin")
  @PostAuthorize("hasRole('ADMIN')")
  public UserInfoResponse authenticateAdmin(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
	  return this.authservice.authenticateUser(loginRequest, response);
  }

  @PostMapping("/signup/user")  
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
      return this.authservice.registerUser(signUpRequest);
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerAdmin(@Valid @RequestBody SignupRequest signUpRequest) {
    return this.authservice.registerAdmin(signUpRequest);
  }
  
  @PostMapping("/refresh-token")
  public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) {
      return this.authservice.refreshToken(request, response);
  }

  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    return this.authservice.logoutUser();
  }
}