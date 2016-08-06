package br.com.checkpoint.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.checkpoint.model.security.CredentialRequest;
import br.com.checkpoint.security.TokenUtils;

@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private UserDetailsService userDetailsService;

	@RequestMapping(value = "/api/login", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> authenticationRequest(@RequestBody CredentialRequest request) throws AuthenticationException {

		System.out.println(request.getLogin() + " - " +request.getPassword() );
		// Perform the authentication
		Authentication authentication = authenticate(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		System.out.println("passou aqui");
		// Reload password post-authentication so we can generate token
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getLogin());
		String token = this.tokenUtils.generateToken(userDetails);

		// Return the token
		JSONObject response = new JSONObject().put("token", token);
//		return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
		return ResponseEntity.ok(response.toString());
	}

	private Authentication authenticate(CredentialRequest request) {
		return this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
	}
}
