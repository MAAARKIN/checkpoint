package br.com.checkpoint.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
	
	private Map<Integer, String> users;
	
	public UserController() {
		this.users = new HashMap<>();
		this.users.put(1, "Marcos");
		this.users.put(2, "Jésssica");
		this.users.put(3, "Fulano");
	}

	@RequestMapping(path="/users/{id}", method=RequestMethod.GET)
    public ResponseEntity<?> getUsuario(@PathVariable Integer id) {
		String usuario = this.users.get(id);
		System.out.println(usuario);
		
		return ResponseEntity.ok(usuario);
    }
	
	@RequestMapping(path="/users", method=RequestMethod.GET)
    public ResponseEntity<?> listarUsuarios() {
		
		return ResponseEntity.ok(this.users.values());
    } 
}
