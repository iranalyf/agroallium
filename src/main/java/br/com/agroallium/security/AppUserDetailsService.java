package br.com.agroallium.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.agroallium.models.Usuario;
import br.com.agroallium.repository.Usuarios;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private Usuarios usuarios;

	@Override
	public UserDetails loadUserByUsername(String cpfOuCnpj) throws UsernameNotFoundException {
		Optional<Usuario> usuariosOptional = this.usuarios.findByCpfOuCnpj(cpfOuCnpj);
		Usuario usuario = usuariosOptional
				.orElseThrow(() -> new UsernameNotFoundException("CPF ou CNPJ e/ou Senha inválidos"));
		return new UsuarioSistema(usuario, getPermissoes(usuario));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		return authorities;
	}

}
