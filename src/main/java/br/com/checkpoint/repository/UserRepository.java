package br.com.checkpoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.checkpoint.model.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Long> {

	public Usuario findByLogin(String nome);
}
