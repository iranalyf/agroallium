package br.com.agroallium.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorsController {

	@GetMapping("/404")
	public String paginaNaoEncontrada() {
		return "error/404";
	}
	
	@RequestMapping("/500")
	public String erroServidor() {
		return "error/500";
	}
}
