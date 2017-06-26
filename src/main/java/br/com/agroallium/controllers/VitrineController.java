package br.com.agroallium.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.agroallium.services.ProdutosService;

@Controller
@RequestMapping("/vitrine")
public class VitrineController {

	@Autowired
	private ProdutosService produtosService;

	@GetMapping
	public ModelAndView vitrine() {
		ModelAndView mv = new ModelAndView("vitrine/Vitrine");

		mv.addObject("produtos", this.produtosService.findAll());
		return mv;
	}
}
