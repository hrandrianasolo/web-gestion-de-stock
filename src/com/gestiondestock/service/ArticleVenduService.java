package com.gestiondestock.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gestiondestock.model.ArticleVendu;

public class ArticleVenduService {

	@PersistenceContext
	private EntityManager entityManager;

	public ArticleVenduService(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public ArticleVendu findById(Integer id) {
		ArticleVendu articleVendu = entityManager.createQuery("SELECT b FROM ArticleVendu b WHERE b.id = :id", ArticleVendu.class)
				.setParameter("id", id).getSingleResult();
		return articleVendu;
	}

	public List<ArticleVendu> findAll() {
		List<ArticleVendu> tmp = new ArrayList<ArticleVendu>();

		tmp = entityManager.createQuery("SELECT g FROM ArticleVendu g", ArticleVendu.class).getResultList();
		for(ArticleVendu articleVendu : tmp) {
			entityManager.refresh(articleVendu);
			entityManager.refresh(articleVendu.getTransactionCaisse());
		}
		return tmp;
	}

	public Optional<ArticleVendu> save(ArticleVendu articleVendu) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(articleVendu);
			entityManager.getTransaction().commit();
			return Optional.of(articleVendu);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public Optional<ArticleVendu> update(ArticleVendu articleVendu, ArticleVendu reference) {
		try {
			
			entityManager.getTransaction().begin();
			articleVendu.setArticle(reference.getArticle());
			articleVendu.setNombre(reference.getNombre());
			articleVendu.setTransactionCaisse(reference.getTransactionCaisse());
			entityManager.merge(articleVendu);
			entityManager.getTransaction().commit();

			return Optional.of(articleVendu);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public void remove(Integer id) {
		try {
			ArticleVendu reference = entityManager.getReference(ArticleVendu.class, id);
			entityManager.getTransaction().begin();
			entityManager.remove(reference);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}