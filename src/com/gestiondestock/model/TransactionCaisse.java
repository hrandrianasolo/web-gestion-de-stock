package com.gestiondestock.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TransactionCaisse {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToMany(mappedBy = "transactionCaisse", cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<ArticleVendu> articleVendus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Collection<ArticleVendu> getArticleVendus() {
		return articleVendus;
	}

	public void setArticleVendus(Collection<ArticleVendu> articleVendus) {
		this.articleVendus = articleVendus;
	}

}
