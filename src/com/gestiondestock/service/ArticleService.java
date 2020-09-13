package com.gestiondestock.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.gestiondestock.model.Article;

public class ArticleService {

	@PersistenceContext
	private EntityManager entityManager;

	public ArticleService(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public Article findById(Integer id) {
		Article article = entityManager.createQuery("SELECT b FROM Article b WHERE b.id = :id", Article.class)
				.setParameter("id", id).getSingleResult();
		return article;
	}

	public List<Article> findAll() {
		List<Article> tmp = new ArrayList<Article>();

		tmp = entityManager.createQuery("SELECT g FROM Article g", Article.class).getResultList();
		for (Article article : tmp) {
			entityManager.refresh(article);
			entityManager.refresh(article.getFournisseur());
		}
		return tmp;
	}

	public Optional<Article> findByCodebar(Integer codebar) {
		Optional<Article> optArticle = null;
		
		try {
			Article article = entityManager
					.createQuery("SELECT b FROM Article b WHERE b.codebar = :codebar", Article.class)
					.setParameter("codebar", codebar).getSingleResult();
			optArticle = Optional.of(article);
		} catch (NoResultException e) {
			optArticle = Optional.empty();
		}

		return optArticle;
	}

	public Optional<Article> save(Article article) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(article);
			entityManager.getTransaction().commit();
			return Optional.of(article);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public Optional<Article> update(Article article, Article reference) {
		try {

			entityManager.getTransaction().begin();
			article.setNom(reference.getNom());
			article.setPrix(reference.getPrix());
			article.setSeuil(reference.getSeuil());
			article.setStock(reference.getStock());
			article.setIstVenduEnDetail(reference.getIstVenduEnDetail());
			article.setFournisseur(reference.getFournisseur());
			article.setCodebar(reference.getCodebar());
			entityManager.merge(article);
			entityManager.getTransaction().commit();

			return Optional.of(article);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public void remove(Integer id) {
		try {
			Article reference = entityManager.getReference(Article.class, id);
			entityManager.getTransaction().begin();
			entityManager.remove(reference);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Article> findAllByStockAndSeuil() {
		List<Article> tmp = new ArrayList<Article>();

		tmp = entityManager.createQuery("SELECT g FROM Article g WHERE g.stock <= g.seuil", Article.class)
				.getResultList();
		for (Article article : tmp) {
			entityManager.refresh(article);
			entityManager.refresh(article.getFournisseur());
		}
		return tmp;
	}

}