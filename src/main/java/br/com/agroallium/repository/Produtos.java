package br.com.agroallium.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.agroallium.models.Produto;
import br.com.agroallium.repository.helper.produtos.ProdutosQuery;

public interface Produtos extends JpaRepository<Produto, Long>, ProdutosQuery {

	

}
