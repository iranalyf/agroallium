package br.com.agroallium.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.agroallium.infra.FileSaver;
import br.com.agroallium.models.Estado;
import br.com.agroallium.models.Regiao;
import br.com.agroallium.models.TipoPessoa;
import br.com.agroallium.models.Usuario;
import br.com.agroallium.repository.Produtos;
import br.com.agroallium.security.UsuarioSistema;
import br.com.agroallium.services.UsuariosService;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	private UsuariosService usuariosService;

	@Autowired
	private Produtos produtos;

	@Autowired
	private FileSaver fileSaver;

	@GetMapping("/novo")
	public ModelAndView novo(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuarios/cadastroUsuario");

		mv.addObject("usuario", usuario);
		mv.addObject("tiposPessoa", TipoPessoa.values());
		mv.addObject("ufs", Estado.values());
		mv.addObject("regioes", Regiao.values());
		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView novo(MultipartFile file, @Valid Usuario usuario, BindingResult result,
			RedirectAttributes atributes) {

		if (result.hasErrors()) {
			return novo(usuario);
		}

		if (file != null && !file.getOriginalFilename().isEmpty()) {
			String path = fileSaver.write("files", file);
			usuario.setLogoMarca(path);
		}

		this.usuariosService.salvar(usuario);
		atributes.addFlashAttribute("mensagem", String.format("Usuario %s Cadastrado com Sucesso.", usuario.getNome()));

		return new ModelAndView("redirect:/usuarios/novo");
	}

	@GetMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable Long id, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		Usuario usuarioDetalhe = this.usuariosService.findById(id);

		ModelAndView mv = new ModelAndView("usuarios/detalhe");
		mv.addObject("usuarioDetalhe", usuarioDetalhe);
		mv.addObject("produtosCadastrados",
				this.produtos.produtosCadastrados(usuarioSistema.getUsuario().getIdUsuario()));
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Long id) {

		Usuario usuario = this.usuariosService.findById(id);

		ModelAndView mv = novo(usuario);
		return mv;
	}

	@GetMapping("/api")
	public @ResponseBody List<Usuario> buscaTodos() {
		return this.usuariosService.findAll();
	}
}
