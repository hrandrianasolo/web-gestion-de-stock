package com.gestiondestock.controller;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestiondestock.forms.FournisseurForm;
import com.gestiondestock.model.Fournisseur;
import com.gestiondestock.service.FournisseurService;

/**
 * Servlet implementation class FournisseurController
 */
@WebServlet(name = "FournisseurNew", urlPatterns = "/newfournisseur")
public class FournisseurNew extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FournisseurService fournisseurService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public FournisseurNew() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/newfournisseur.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (fournisseurService == null) {
			EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
			EntityManager em = emf.createEntityManager();

			fournisseurService = new FournisseurService(em);
		}

		FournisseurForm fournisseurForm = new FournisseurForm();
		Fournisseur fournisseur = fournisseurForm.nouveauFournisseur(request);
		fournisseurService.save(fournisseur);

		response.sendRedirect(request.getContextPath() + "/fournisseurs");
	}

}
