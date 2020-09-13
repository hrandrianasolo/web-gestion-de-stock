package com.gestiondestock.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestiondestock.model.Fournisseur;
import com.gestiondestock.service.FournisseurService;

/**
 * Servlet implementation class FournisseurController
 */
@WebServlet(name = "FournisseurController", urlPatterns = "/fournisseurs")
public class FournisseurController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FournisseurService fournisseurService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public FournisseurController() {
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

		if (fournisseurService == null) {
			EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
			EntityManager em = emf.createEntityManager();

			fournisseurService = new FournisseurService(em);
		}

		if (request.getParameter("delete") != null) {
			Integer id = Integer.parseInt(request.getParameter("delete"));
			if (id != null) {
				fournisseurService.remove(id);
			}
		} else if (request.getParameter("fournisseur") != null) {

		}

		//// eto blem rehefa avy manao mis a jour mety base de donner fa rehefa maka de tsy mety
		List<Fournisseur> fournisseurs = fournisseurService.findAll();
		request.setAttribute("fournisseurs", fournisseurs);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/fournisseur.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
