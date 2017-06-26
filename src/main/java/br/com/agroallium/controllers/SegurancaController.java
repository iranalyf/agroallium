package br.com.agroallium.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SegurancaController {

	@GetMapping("/login")
	public String form(@AuthenticationPrincipal User user) {
		if(user != null){
			return "/vitrine";
		}
		return "Home";
	}

	@GetMapping("/403")
	public String acessoNegado() {

		return "403";
	}
}
