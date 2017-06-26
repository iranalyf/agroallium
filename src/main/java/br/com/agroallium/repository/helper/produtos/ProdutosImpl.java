package br.com.agroallium.repository.helper.produtos;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.agroallium.dto.ProdutosAno;
import br.com.agroallium.models.Produto;

public class ProdutosImpl implements ProdutosQuery {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Produto> buscaProdutosPorUsuario(Long codigo) {

		return this.em.createQuery("from Produto where usuario_id = :codigo ", Produto.class)
				.setParameter("codigo", codigo).getResultList();
	}

	@Override
	public Long produtosCadastrados(Long id) {
		Optional<Long> optional = Optional.ofNullable(
				this.em.createQuery("select count(idProduto) from Produto where usuario_id = :codigo", Long.class)
						.setParameter("codigo", id).getSingleResult());
		return optional.orElse(new Long(0));
	}

	@Override
	public Produto buscaRegiao(Long id) {

		return this.em
				.createQuery("select u.regiao from Produto p join Usuario u where p idProduto = :codigo", Produto.class)
				.setParameter("codigo", id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProdutosAno> totalPorMes() {
		List<ProdutosAno> vendasAno = this.em.createNamedQuery("Produtos.totalPorMes").getResultList();

		return vendasAno;
	}

	@Override
	public List<ProdutosAno> totalPorAno() {
		@SuppressWarnings("unchecked")
		List<ProdutosAno> vendasAno = this.em.createNamedQuery("Produtos.totalPorMes").getResultList();

		return vendasAno;
	}

	@Override
	public List<Produto> findByName(String nome) {

		return this.em.createQuery("from Produto where lower(descricao) like :nome", Produto.class)
				.setParameter("nome", nome.toLowerCase() + "%").getResultList();
	}

	@Override
	public List<Produto> findByValorMaximo(BigDecimal valor) {

		return this.em.createQuery("from Produto where valor <= :valorMaximo", Produto.class)
				.setParameter("valorMaximo", valor).getResultList();
	}

}
