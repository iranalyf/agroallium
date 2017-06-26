package br.com.agroallium.repository.helper.produtos;

import java.math.BigDecimal;
import java.util.List;

import br.com.agroallium.dto.ProdutosAno;
import br.com.agroallium.models.Produto;

public interface ProdutosQuery {

	List<Produto> buscaProdutosPorUsuario(Long codigo);

	Long produtosCadastrados(Long id);

	Produto buscaRegiao(Long id);

	public List<Produto> findByName(String nome);

	public List<Produto> findByValorMaximo(BigDecimal valor);

	public List<ProdutosAno> totalPorMes();

	public List<ProdutosAno> totalPorAno();
}
