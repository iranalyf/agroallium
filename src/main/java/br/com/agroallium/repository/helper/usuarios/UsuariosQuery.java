package br.com.agroallium.repository.helper.usuarios;

import java.util.List;
import java.util.Optional;

import br.com.agroallium.models.Usuario;

public interface UsuariosQuery {

	public Optional<Usuario> findByCpfOuCnpj(String cpfOuCnpj);

	List<String> permissoes(Usuario usuario);
	
}
