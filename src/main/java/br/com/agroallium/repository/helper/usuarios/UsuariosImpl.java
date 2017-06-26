package br.com.agroallium.repository.helper.usuarios;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.agroallium.models.Usuario;

public class UsuariosImpl implements UsuariosQuery {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Optional<Usuario> findByCpfOuCnpj(String cpfOuCnpj) {

		return em.createQuery("from Usuario where cpfOuCnpj = :cpfOuCnpj",Usuario.class)
				.setParameter("cpfOuCnpj", cpfOuCnpj)
				.getResultList().stream().findFirst();
	}

	@Override
	public List<String> permissoes(Usuario usuario) {

		return null;
	}
}
