package br.com.agroallium.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.agroallium.models.Produto;
import br.com.agroallium.repository.Produtos;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ProdutosService {

	@Autowired
	private Produtos produtos;

	@Transactional(readOnly = false)
	public void salvar(Produto produto) {
		if (produto.getIdProduto() == null) {
			produto.setDataCadastro(LocalDate.now());
		}
		this.produtos.save(produto);
	}

	public List<Produto> findAll() {
		return this.produtos.findAll();
	}

	public Produto findById(Long id) {
		return this.produtos.findOne(id);
	}
}
