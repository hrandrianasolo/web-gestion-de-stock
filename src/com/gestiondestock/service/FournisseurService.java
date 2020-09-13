package com.gestiondestock.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gestiondestock.model.Fournisseur;

public class FournisseurService {

	@PersistenceContext
	private EntityManager entityManager;

	public FournisseurService(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public Fournisseur findById(Integer id) {
		Fournisseur fournisseur = entityManager
				.createQuery("SELECT b FROM Fournisseur b WHERE b.id = :id", Fournisseur.class).setParameter("id", id)
				.getSingleResult();
		return fournisseur;
	}

	public List<Fournisseur> findAll() {
		List<Fournisseur> tmp = new ArrayList<Fournisseur>();

		tmp = entityManager.createQuery("SELECT g FROM Fournisseur g", Fournisseur.class).getResultList();
		for (Fournisseur fournisseur : tmp) {
			entityManager.refresh(fournisseur);
		}
		return tmp;
	}

	public Optional<Fournisseur> save(Fournisseur fournisseur) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(fournisseur);
			entityManager.getTransaction().commit();
			return Optional.of(fournisseur);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public Optional<Fournisseur> update(Fournisseur fournisseur, Fournisseur reference) {
		try {

			entityManager.getTransaction().begin();
			fournisseur.setNom(reference.getNom());
			fournisseur.setAdresse(reference.getAdresse());
			fournisseur.setCodePostal(reference.getCodePostal());
			fournisseur.setTelephone(reference.getTelephone());

			entityManager.merge(fournisseur);
			entityManager.getTransaction().commit();

			return Optional.of(fournisseur);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public void remove(Integer id) {
		try {
			Fournisseur reference = entityManager.getReference(Fournisseur.class, id);
			entityManager.getTransaction().begin();
			entityManager.remove(reference);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}