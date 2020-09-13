package com.gestiondestock.forms;

import javax.servlet.http.HttpServletRequest;

import com.gestiondestock.model.Fournisseur;

public final class FournisseurForm {

	private static final String champ_id = "id";
	private static final String champ_nom = "nom";
	private static final String champ_adresse = "adresse";
	private static final String champ_codePostal = "codePostal";
	private static final String champ_telephone = "telephone";

	private String resultat;

	public String getResultat() {
		return resultat;
	}

	public Fournisseur nouveauFournisseur(HttpServletRequest request) {
		Integer id = null;
		if(getValeurChamp(request, champ_id) != null) {
			id = Integer.parseInt(getValeurChamp(request, champ_id));
		}
		String nom = getValeurChamp(request, champ_nom);
		String adresse = getValeurChamp(request, champ_adresse);
		Integer codePostal = Integer.parseInt(getValeurChamp(request, champ_codePostal));
		String telephone = getValeurChamp(request, champ_telephone);
		
		Fournisseur fournisseur = new Fournisseur();

		fournisseur.setId(id);
		fournisseur.setNom(nom);
		fournisseur.setAdresse(adresse);
		fournisseur.setCodePostal(codePostal);
		fournisseur.setTelephone(telephone);
		
		return fournisseur;
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
