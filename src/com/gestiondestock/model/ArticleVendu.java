package com.gestiondestock.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ArticleVendu {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer nombre;
	@OneToOne
	private Article article;

	@ManyToOne(fetch = FetchType.EAGER)
	private TransactionCaisse transactionCaisse;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNombre() {
		return nombre;
	}

	public void setNombre(Integer nombre) {
		this.nombre = nombre;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public TransactionCaisse getTransactionCaisse() {
		return transactionCaisse;
	}

	public void setTransactionCaisse(TransactionCaisse transactionCaisse) {
		this.transactionCaisse = transactionCaisse;
	}

}
