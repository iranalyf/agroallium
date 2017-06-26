package br.com.agroallium.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.agroallium.models.Usuario;
import br.com.agroallium.repository.Usuarios;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class UsuariosService {

	@Autowired
	private Usuarios usuarios;

	@Transactional(readOnly = false)
	public void salvar(Usuario usuario) {
		if (usuario.getIdUsuario() == null) {
			usuario.setDataCadastro(LocalDate.now());
		}
		this.usuarios.save(usuario);
	}

	public List<Usuario> findAll() {
		return usuarios.findAll();
	}

	public Usuario findById(Long id) {
		return usuarios.findOne(id);
	}
}
