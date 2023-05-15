package com.university.managemant.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.university.managemant.config.JwtTokenUtil;
import com.university.managemant.model.User;
import com.university.managemant.requestRespondseHandler.JwtRequest;
import com.university.managemant.requestRespondseHandler.JwtResponse;
import com.university.managemant.requestRespondseHandler.UserDto;
import com.university.managemant.service.JwtUserDetailsService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDto user) throws Exception {
		Map<String, Object> response = new HashMap<>();
		
		if(!user.getUserType().equalsIgnoreCase("Admin")) {
			userDetailsService.setProfile(user);
			User newUser = userDetailsService.createUserAccount(user);
			if(newUser!=null) {
				response.put("success", true);
				response.put("status", HttpStatus.OK.value());
				response.put("msg", "you have done it");
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}else {
				response.put("success", false);
				response.put("status", HttpStatus.BAD_REQUEST.value());
				response.put("msg", "sorry, try again!");
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}
			
		}else {
			response.put("success", false);
			response.put("status", HttpStatus.BAD_REQUEST.value());
			response.put("msg", "Admin cannot be register");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		
	}

	private void authenticate(String email, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
