package br.com.agroallium.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.agroallium.models.Produto;
import br.com.agroallium.repository.Produtos;
import br.com.agroallium.session.CarrinhoComprasSession;
import br.com.agroallium.session.CarrinhoItem;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

	@Autowired
	private Produtos produtos;

	@Autowired
	private CarrinhoComprasSession carrinho;

	@GetMapping("/add/{id}")
	public ModelAndView add(@PathVariable("id") Long idProduto) {
		
		ModelAndView mv = new ModelAndView("redirect:/carrinho");
		CarrinhoItem carrinhoItem = criaItem(idProduto);
		this.carrinho.add(carrinhoItem);
		
		return mv;
	}

	@GetMapping
	public ModelAndView itens() {
		ModelAndView mv = new ModelAndView("carrinho/carrinho");
		mv.addObject("itens", this.carrinho.getItens());
		return mv;
	}

	private CarrinhoItem criaItem(Long idProduto) {
		Produto produto = this.produtos.findOne(idProduto);
		CarrinhoItem carrinhoItem = new CarrinhoItem(produto);

		return carrinhoItem;
	}
}
