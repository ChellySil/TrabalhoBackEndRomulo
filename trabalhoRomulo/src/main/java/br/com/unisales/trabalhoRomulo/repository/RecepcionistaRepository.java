package br.com.unisales.trabalhoRomulo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.unisales.trabalhoRomulo.model.UsuarioRecepcionista;

public interface RecepcionistaRepository extends JpaRepository <UsuarioRecepcionista, Integer> {
	
	

}
