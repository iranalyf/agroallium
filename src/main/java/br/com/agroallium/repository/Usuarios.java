package br.com.agroallium.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.agroallium.models.Usuario;
import br.com.agroallium.repository.helper.usuarios.UsuariosQuery;

public interface Usuarios extends JpaRepository<Usuario, Long>,UsuariosQuery {

}
