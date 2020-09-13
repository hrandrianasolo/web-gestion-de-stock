package com.gestiondestock.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gestiondestock.model.ArticleVendu;
import com.gestiondestock.model.TransactionCaisse;

public class TransactionCaisseService {

	@PersistenceContext
	private EntityManager entityManager;

	public TransactionCaisseService(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public TransactionCaisse findById(Integer id) {
		TransactionCaisse transactionCaisse = entityManager
				.createQuery("SELECT b FROM TransactionCaisse b WHERE b.id = :id", TransactionCaisse.class)
				.setParameter("id", id).getSingleResult();
		return transactionCaisse;
	}

	public List<TransactionCaisse> findAll() {
		List<TransactionCaisse> tmp = new ArrayList<TransactionCaisse>();

		tmp = entityManager.createQuery("SELECT g FROM TransactionCaisse g", TransactionCaisse.class).getResultList();
		for (TransactionCaisse transactionCaisse : tmp) {
			entityManager.refresh(transactionCaisse);
			entityManager.refresh(transactionCaisse.getArticleVendus());
		}
		return tmp;
	}

	public Optional<TransactionCaisse> save(TransactionCaisse transactionCaisse) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(transactionCaisse);
			for (ArticleVendu tmp : transactionCaisse.getArticleVendus()) {
				tmp.setTransactionCaisse(transactionCaisse);
				entityManager.persist(tmp);
			}
			entityManager.getTransaction().commit();

			return Optional.of(transactionCaisse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public Optional<TransactionCaisse> update(TransactionCaisse transactionCaisse, TransactionCaisse reference) {
		try {

			entityManager.getTransaction().begin();
			transactionCaisse.setArticleVendus(reference.getArticleVendus());
			entityManager.merge(transactionCaisse);
			entityManager.getTransaction().commit();

			return Optional.of(transactionCaisse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public void remove(Integer id) {
		try {
			TransactionCaisse reference = entityManager.getReference(TransactionCaisse.class, id);
			entityManager.getTransaction().begin();
			entityManager.remove(reference);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}