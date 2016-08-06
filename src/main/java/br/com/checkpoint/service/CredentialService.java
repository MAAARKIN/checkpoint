package br.com.checkpoint.service;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.checkpoint.model.Usuario;
import br.com.checkpoint.model.security.CredentialFactory;
import br.com.checkpoint.repository.UserRepository;

@Service
public class CredentialService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Chamando o loadUserByUsername");
		Usuario u = userRepository.findByLogin(username);
		if (u == null) throw new UsernameNotFoundException(MessageFormat.format("No user found with username: {0}", username));
		return CredentialFactory.create(u);
	}

}
