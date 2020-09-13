package com.gestiondestock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nom;
	private Integer stock;
	private Integer seuil;
	private Integer codebar;
	private Float prix;
	@Column(name = "venduendetail")
	private Boolean istVenduEnDetail;

	@ManyToOne(fetch = FetchType.LAZY)
	private Fournisseur fournisseur;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getSeuil() {
		return seuil;
	}

	public void setSeuil(Integer seuil) {
		this.seuil = seuil;
	}

	public Integer getCodebar() {
		return codebar;
	}

	public void setCodebar(Integer codebar) {
		this.codebar = codebar;
	}

	public Float getPrix() {
		return prix;
	}

	public void setPrix(Float prix) {
		this.prix = prix;
	}

	public Boolean getIstVenduEnDetail() {
		return istVenduEnDetail;
	}

	public void setIstVenduEnDetail(Boolean istVenduEnDetail) {
		this.istVenduEnDetail = istVenduEnDetail;
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codebar == null) ? 0 : codebar.hashCode());
		result = prime * result + ((fournisseur == null) ? 0 : fournisseur.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((istVenduEnDetail == null) ? 0 : istVenduEnDetail.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prix == null) ? 0 : prix.hashCode());
		result = prime * result + ((seuil == null) ? 0 : seuil.hashCode());
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (codebar == null) {
			if (other.codebar != null)
				return false;
		} else if (!codebar.equals(other.codebar))
			return false;
		if (fournisseur == null) {
			if (other.fournisseur != null)
				return false;
		} else if (!fournisseur.equals(other.fournisseur))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (istVenduEnDetail == null) {
			if (other.istVenduEnDetail != null)
				return false;
		} else if (!istVenduEnDetail.equals(other.istVenduEnDetail))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prix == null) {
			if (other.prix != null)
				return false;
		} else if (!prix.equals(other.prix))
			return false;
		if (seuil == null) {
			if (other.seuil != null)
				return false;
		} else if (!seuil.equals(other.seuil))
			return false;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		return true;
	}

	public Article(Integer id, String nom, Integer stock, Integer seuil, Integer codebar, Float prix,
			Boolean istVenduEnDetail, Fournisseur fournisseur) {
		super();
		this.id = id;
		this.nom = nom;
		this.stock = stock;
		this.seuil = seuil;
		this.codebar = codebar;
		this.prix = prix;
		this.istVenduEnDetail = istVenduEnDetail;
		this.fournisseur = fournisseur;
	}

	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", nom=" + nom + ", stock=" + stock + ", seuil=" + seuil + ", codebar=" + codebar
				+ ", prix=" + prix + ", istVenduEnDetail=" + istVenduEnDetail + ", fournisseur=" + fournisseur + "]";
	}

}
