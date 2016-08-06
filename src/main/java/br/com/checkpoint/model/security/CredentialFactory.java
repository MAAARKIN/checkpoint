package br.com.checkpoint.model.security;

import java.util.Date;

import org.springframework.security.core.authority.AuthorityUtils;

import br.com.checkpoint.model.Usuario;

public class CredentialFactory {

	public static Credential create(Usuario u) {
		return new Credential(u.getId(), u.getLogin(), u.getSenha(), "", new Date(), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}
}
