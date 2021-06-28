package br.com.adamis.auth.repository.usuarios;

import br.com.adamis.auth.entity.Usuarios;
import br.com.adamis.auth.entity.Usuarios_;
import br.com.adamis.auth.filter.UsuariosFilter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.util.StringUtils;

public class UsuariosRepositoryImpl implements UsuariosRepositoryQuery {

	@PersistenceContext private EntityManager manager;

	@Override
	public Page<Usuarios> filtrar(UsuariosFilter usuariosFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Usuarios> criteria = builder.createQuery(Usuarios.class);
		Root<Usuarios> root = criteria.from(Usuarios.class);

		List<Order> orders = QueryUtils.toOrders(pageable.getSort(), root, builder);

		Predicate[] predicates = criarRestricoes(usuariosFilter, builder, root);
		criteria.where(predicates).orderBy(orders);

		TypedQuery<Usuarios> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(usuariosFilter));
	}

	private Predicate[] criarRestricoes(UsuariosFilter usuariosFilter, CriteriaBuilder builder, Root<Usuarios> root) {
		List<Predicate> predicates = new ArrayList<>();

		// ID
		if (usuariosFilter.getId() != null) {
			predicates.add(builder.equal(root.get(Usuarios_.ID), usuariosFilter.getId()));
		}
		
		// SENHA
		if (StringUtils.hasLength(usuariosFilter.getSenha())) {
			predicates.add(
					builder.like(
							builder.lower(root.get(Usuarios_.SENHA)),
							"%" + usuariosFilter.getSenha().toLowerCase() + "%"));
		}

		// TOKEN
		if (StringUtils.hasLength(usuariosFilter.getToken())) {
			predicates.add(
					builder.like(
							builder.lower(root.get(Usuarios_.TOKEN)),
							"%" + usuariosFilter.getToken().toLowerCase() + "%"));
		}

		// USUARIO
		if (StringUtils.hasLength(usuariosFilter.getUsuario())) {
			predicates.add(
					builder.like(
							builder.lower(root.get(Usuarios_.USUARIO)),
							"%" + usuariosFilter.getUsuario().toLowerCase() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Long total(UsuariosFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Usuarios> root = criteria.from(Usuarios.class);

		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
