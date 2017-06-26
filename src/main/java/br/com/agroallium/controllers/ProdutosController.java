package br.com.agroallium.controllers;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.agroallium.dto.ProdutosAno;
import br.com.agroallium.models.Produto;
import br.com.agroallium.repository.Produtos;
import br.com.agroallium.security.UsuarioSistema;
import br.com.agroallium.services.ProdutosService;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

	@Autowired
	private ProdutosService produtosService;

	@Autowired
	private Produtos produtos;

	@GetMapping("/novo")
	public ModelAndView novo(Produto produto) {

		ModelAndView mv = new ModelAndView("produtos/cadastroProduto");
		mv.addObject("produto", produto);
		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView novo(@AuthenticationPrincipal UsuarioSistema usuarioSistema, @Valid Produto produto,
			BindingResult result, RedirectAttributes atributes) {

		if (result.hasErrors()) {
			return novo(produto);
		}

		produto.setUsuario(usuarioSistema.getUsuario());
		this.produtosService.salvar(produto);
		atributes.addFlashAttribute("mensagem", "Produto cadastrado com sucesso.");
		return new ModelAndView("redirect:/produtos/novo");
	}

	@GetMapping("/filtrarPorNome")
	public List<Produto> buscaPorNome(String nome) {
		return this.produtos.findByName(nome);
	}

	@GetMapping("/filtrarPorPrecoMaximo")
	public List<Produto> buscaPorPrecoMaximo(BigDecimal valor) {
		return this.produtos.findByValorMaximo(valor);
	}

	@GetMapping("/porUsuario")
	public ModelAndView buscarProdutosUsuarioLogado(@AuthenticationPrincipal UsuarioSistema usuarioSistema) {

		ModelAndView mv = new ModelAndView("produtos/pesquisaProdutos2");

		List<Produto> produtos = 
				this.produtos.buscaProdutosPorUsuario(usuarioSistema.getUsuario().getIdUsuario());

		mv.addObject("produtosPorUsuario", produtos);
		return mv;
	}

	@GetMapping("/totalPorMes")
	public @ResponseBody List<ProdutosAno> listarTotalProdutosPorAno() {
		return this.produtos.totalPorMes();
	}

	@GetMapping("/totalPorAno")
	public @ResponseBody List<ProdutosAno> listarTotalProdutosPorMes() {
		return this.produtos.totalPorAno();
	}

	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		Produto produto = this.produtosService.findById(id);
		ModelAndView mv = novo(produto);

		return mv;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> exluir(@PathVariable("id") Long id, RedirectAttributes attributes) {
		Produto produto = this.produtosService.findById(id);

		produtos.delete(produto);
		attributes.addFlashAttribute("mensagem", String.format("%s excluído com Sucesso.", produto.getDescricao()));
		return ResponseEntity.ok().build();
	}

	@GetMapping("/api")
	public @ResponseBody List<Produto> buscarTodos() {
		return this.produtosService.findAll();
	}
}
