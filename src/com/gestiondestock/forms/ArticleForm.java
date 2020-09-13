package com.gestiondestock.forms;

import javax.servlet.http.HttpServletRequest;

import com.gestiondestock.model.Article;
import com.gestiondestock.service.FournisseurService;

public final class ArticleForm {

	private static final String champ_id = "id";
	private static final String champ_nom = "nom";
	private static final String champ_stock = "stock";
	private static final String champ_seuil = "seuil";
	private static final String champ_prix = "prix";
	private static final String champ_istVenduEnDetail = "istVenduEnDetail";
	private static final String champ_codebar = "codebar";
	private static final String champ_fournisseur = "fournisseur";

	private String resultat;
	private FournisseurService fournisseurService;

	public ArticleForm(FournisseurService fournisseurService) {
		super();
		this.fournisseurService = fournisseurService;
	}

	public String getResultat() {
		return resultat;
	}

	public Article nouveauArticle(HttpServletRequest request) {
		Integer id = null;
		if (getValeurChamp(request, champ_id) != null) {
			id = Integer.parseInt(getValeurChamp(request, champ_id));
		}
		String nom = getValeurChamp(request, champ_nom);
		Integer stock = Integer.parseInt(getValeurChamp(request, champ_stock));
		Integer seuil = Integer.parseInt(getValeurChamp(request, champ_seuil));
		Float prix = Float.parseFloat(getValeurChamp(request, champ_prix));
		Boolean istVenduEnDetail = false;
		if (Integer.parseInt(getValeurChamp(request, champ_istVenduEnDetail)) == 0) {
			istVenduEnDetail = false;
		} else {
			istVenduEnDetail = true;
		}
		Integer codebar = Integer.parseInt(getValeurChamp(request, champ_codebar));
		Integer fournisseur = Integer.parseInt(getValeurChamp(request, champ_fournisseur));

		Article article = new Article();

		article.setId(id);
		article.setNom(nom);
		article.setStock(stock);
		article.setSeuil(seuil);
		article.setPrix(prix);
		article.setIstVenduEnDetail(istVenduEnDetail);
		article.setCodebar(codebar);
		article.setFournisseur(fournisseurService.findById(fournisseur));

		return article;
	}

	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur.trim();
		}
	}

}
